package au.com.permeance.cucumber;

import cucumber.runtime.CucumberException;
import gherkin.formatter.Formatter;
import gherkin.formatter.JSONFormatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerFormatter implements Formatter, Reporter {

    // TODO Surely there's a more complete list somewhere than needing to maintain it here...
    private static final Map<String, String> MIME_TYPES_EXTENSIONS = new HashMap<String, String>();

    static {
        MIME_TYPES_EXTENSIONS.put("image/bmp", "bmp");
        MIME_TYPES_EXTENSIONS.put("image/gif", "gif");
        MIME_TYPES_EXTENSIONS.put("image/jpeg", "jpg");
        MIME_TYPES_EXTENSIONS.put("image/png", "png");
        MIME_TYPES_EXTENSIONS.put("video/ogg", "ogg");
    }

    private final File baseDir;

    private final FreemarkerTemplateRenderer freemarkerTemplateRenderer;

    private final JSONFormatter jsonFormatter;

    private final StringWriter stringWriter;

    private int embeddedIndex = 0;

    public FreemarkerFormatter(final File file) {

        if (file.exists() && !file.isDirectory()) {
            throw new CucumberException("Specified file [" + file + "] exists but is not a directory");
        }

        if (!file.exists() && !file.mkdir()) {
            throw new CucumberException("Specified file [" + file + "] does not exist but could not be created as a directory");
        }

        baseDir = file;

        freemarkerTemplateRenderer = new FreemarkerTemplateRenderer();
        stringWriter = new StringWriter();
        jsonFormatter = new JSONFormatter(stringWriter);
    }

    @Override
    public void before(final Match match,
                       final Result result) {

        jsonFormatter.before(match, result);
    }

    @Override
    public void result(final Result result) {

        jsonFormatter.result(result);
    }

    @Override
    public void after(final Match match,
                      final Result result) {

        jsonFormatter.after(match, result);
    }

    @Override
    public void match(final Match match) {

        jsonFormatter.match(match);
    }

    @Override
    public void embedding(final String mimeType,
                          final byte[] data) {

        if (!MIME_TYPES_EXTENSIONS.containsKey(mimeType)) {
            throw new CucumberException("Unknown mime-type [" + mimeType + "]");
        }

        final String fileName = String.format("embedded-%s.%s",
                                              embeddedIndex++,
                                              MIME_TYPES_EXTENSIONS.get(mimeType)
        );
        final File file = new File(baseDir, fileName);

        try {
            FileUtils.writeByteArrayToFile(file, data);
        }
        catch (final IOException e) {
            throw new CucumberException("Error writing data to file [" + file + "]", e);
        }

        jsonFormatter.embedding(mimeType, fileName.getBytes());
    }

    @Override
    public void write(final String text) {

        jsonFormatter.write(text);
    }

    @Override
    public void uri(final String uri) {

        jsonFormatter.uri(uri);
    }

    @Override
    public void feature(final Feature feature) {

        jsonFormatter.feature(feature);
    }

    @Override
    public void background(final Background background) {

        jsonFormatter.background(background);
    }

    @Override
    public void scenario(final Scenario scenario) {

        jsonFormatter.scenario(scenario);
    }

    @Override
    public void scenarioOutline(final ScenarioOutline scenarioOutline) {

        jsonFormatter.scenarioOutline(scenarioOutline);
    }

    @Override
    public void examples(final Examples examples) {

        jsonFormatter.examples(examples);
    }

    @Override
    public void step(final Step step) {

        jsonFormatter.step(step);
    }

    @Override
    public void eof() {

        jsonFormatter.eof();
    }

    @Override
    public void syntaxError(final String state,
                            final String event,
                            final List<String> legalEvents,
                            final String uri,
                            final Integer line) {

        jsonFormatter.syntaxError(state,
                                  event,
                                  legalEvents,
                                  uri,
                                  line);
    }

    @Override
    public void done() {

        jsonFormatter.done();
        jsonFormatter.close();

        final File file = new File(baseDir, "index.html");
        final String result = freemarkerTemplateRenderer.render(stringWriter.toString(), "cucumber.ftl");

        try {
            FileUtils.writeStringToFile(file, result);
        }
        catch (final IOException e) {
            throw new CucumberException("Error writing report to file [" + file + "]", e);
        }
    }

    @Override
    public void close() {

        // This space intentionally left blank
    }

}
