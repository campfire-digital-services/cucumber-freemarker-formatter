package au.com.permeance.cucumber;

import freemarker.template.Configuration;
import freemarker.template.Template;
import gherkin.deps.com.google.gson.JsonObject;
import org.fest.assertions.Condition;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

public class FreemarkerTemplateRendererTest {

    private FreemarkerTemplateRenderer subject;

    @Before
    public void setUp() throws Exception {

        subject = new FreemarkerTemplateRenderer();
    }

    @Test
    public void testBuildConfiguration() throws Exception {

        final Configuration result = subject.buildConfiguration();

        assertThat(result).isNotNull();
    }

    @Test
    public void testBuildModel() throws Exception {

        final JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty("name", "Martin");

        final Map<String, Object> result = subject.buildModel("{\"name\":\"Martin\"}");

        assertThat(result)
                .hasSize(2)
                .includes(entry("page_title", "Feature report"))
                .includes(entry("results_json", expectedJson));
    }

    @Test
    public void testLoadTemplate() throws Exception {

        final Template result = subject.loadTemplate("cucumber.ftl");

        assertThat(result).satisfies(hasName("cucumber.ftl"));
    }

    private static Condition<Object> hasName(final String name) {

        return new Condition<Object>("has name [" + name + "]") {
            @Override
            public boolean matches(final Object value) {

                return name.equals(((Template) value).getName());
            }
        };
    }

    @Test(expected = RuntimeException.class)
    public void testLoadTemplateWithIOException() throws Exception {

        subject.loadTemplate("unknown.ftl");
    }

    @Test
    public void testRender() throws Exception {

        final String result = subject.render("{\"name\": \"Martin\"}", "simple.ftl");

        assertThat(result).contains("Hi Martin!");
    }

    @Test
    public void testRenderTemplate() throws Exception {

        final Template template = subject.loadTemplate("simple.ftl");

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Martin");

        final Map<String, Object> model = Collections.<String, Object>singletonMap("results_json", jsonObject);

        final String result = subject.renderTemplate(template, model);

        assertThat(result).contains("Hi Martin!");
    }

    @Test
    public void testRenderTemplateToWriter() throws Exception {

        final Template template = subject.loadTemplate("simple.ftl");

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Martin");

        final Map<String, Object> model = Collections.<String, Object>singletonMap("results_json", jsonObject);

        final StringWriter stringWriter = new StringWriter();

        subject.renderTemplateToWriter(template, model, stringWriter);

        assertThat(stringWriter.toString()).contains("Hi Martin!");
    }

    @Test(expected = RuntimeException.class)
    public void testRenderTemplateToWriterWithIOException() throws Exception {

        final Template template = subject.loadTemplate("cucumber.ftl");

        subject.renderTemplateToWriter(template, Collections.<String, Object>emptyMap(), new BrokenWriter());
    }

    @Test(expected = RuntimeException.class)
    public void testRenderTemplateToWriterWithTemplateException() throws Exception {

        final Template template = subject.loadTemplate("broken.ftl");

        subject.renderTemplateToWriter(template, Collections.<String, Object>emptyMap(), new StringWriter());
    }

    private class BrokenWriter extends Writer {

        @Override
        public void write(final char[] cbuf,
                          final int off,
                          final int len) throws IOException {

            throw new IOException();
        }

        @Override
        public void flush() throws IOException {

            throw new IOException();
        }

        @Override
        public void close() throws IOException {

            throw new IOException();
        }
    }

}
