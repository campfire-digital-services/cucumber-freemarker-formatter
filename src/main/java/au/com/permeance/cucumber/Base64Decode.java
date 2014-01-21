package au.com.permeance.cucumber;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.StringUtils.newStringUtf8;

public class Base64Decode implements TemplateMethodModelEx {

    @Override
    public String exec(final List arguments) throws TemplateModelException {

        if (arguments.size() != 1) {
            throw new IllegalArgumentException("Wrong number of arguments to encode (we can only do one) [" + arguments + "]");
        }

        final Object argument = arguments.get(0);

        return argument == null
               ? null
               : newStringUtf8(
                       decodeBase64(
                               argument.toString()
                       )
               );
    }

}
