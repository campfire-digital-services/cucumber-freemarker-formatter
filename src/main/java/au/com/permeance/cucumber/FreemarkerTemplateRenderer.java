package au.com.permeance.cucumber;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gherkin.deps.com.google.gson.JsonParser;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTemplateRenderer {

    public String render(final String json) {

        final Map<String, Object> model = buildModel(json);
        final Template template = loadTemplate();

        return renderTemplate(template, model);
    }

    protected String renderTemplate(final Template template,
                                    final Map<String, Object> model) {

        final StringWriter stringWriter = new StringWriter();

        try {
            template.process(model, stringWriter);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        return stringWriter.toString();
    }

    protected Template loadTemplate() {

        try {
            return buildConfiguration().getTemplate("cucumber.ftl");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Configuration buildConfiguration() {

        final Configuration configuration = new Configuration();

        configuration.addAutoInclude("_init.ftl");
        configuration.addAutoInclude("_macros.ftl");
        configuration.setClassForTemplateLoading(getClass(), "template/");
        configuration.setStrictBeanModels(true);
        configuration.setStrictSyntaxMode(true);
        configuration.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
        configuration.setWhitespaceStripping(true);

        return configuration;
    }

    protected Map<String, Object> buildModel(final String json) {

        final Map<String, Object> model = new HashMap<String, Object>();

        // TODO Read page_title from somewhere else
        model.put("page_title", "Feature report");
        model.put("results_json", new JsonParser().parse(json));

        return model;
    }

}
