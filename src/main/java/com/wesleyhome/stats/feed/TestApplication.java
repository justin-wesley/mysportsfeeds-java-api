package com.wesleyhome.stats.feed;

import com.google.common.base.CaseFormat;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JPackage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestApplication {

    public static void main(String[] args) throws Exception {
        new TestApplication().generate("cumulativeplayerstats");
    }

    private void generate(String name) throws IOException {
        JCodeModel codeModel = new JCodeModel();

        URL source = TestApplication.class.getResource("/data/" + name + ".json");

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return false;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;
            }

            @Override
            public boolean isIncludeGetters() {
                return false;
            }

            @Override
            public boolean isIncludeSetters() {
                return false;
            }

            @Override
            public boolean isIncludeHashcodeAndEquals() {
                return false;
            }

            @Override
            public boolean isIncludeToString() {
                return false;
            }

            @Override
            public boolean isIncludeDynamicBuilders() {
                return false;
            }

        };
        String packageName = "com.wesleyhome.stats.feed.request.api.model." + name;
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        String className = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
        mapper.generate(codeModel, className, packageName, source);
        Map<FieldClass, List<JDefinedClass>> classMappings = stream(codeModel.packages())
                .flatMap(p -> stream(p.classes()))
                .collect(Collectors.groupingBy(FieldClass::new));
        classMappings.forEach(((fieldClass, jDefinedClasses) -> {

        }));
        stream(codeModel.packages())
                .forEach(p -> stream(p.classes())
                        .forEach(cls -> {
                            cls.annotate(Data.class);
                            cls.annotate(AllArgsConstructor.class);
                            cls.annotate(NoArgsConstructor.class);
                            cls.annotate(Builder.class);

                        }));

        Iterator<JPackage> packages = codeModel.packages();
        codeModel.build(Paths.get("src", "main", "java").toFile());

    }

    private <T> Stream<T> stream(Iterator<T> iterator) {
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    private class FieldClass {

        private final Map<String, JFieldVar> fields;

        private FieldClass(JDefinedClass cls) {
            this(cls.fields());
        }

        private FieldClass(Map<String, JFieldVar> fields) {
            this.fields = fields;
        }

        private boolean fieldEquals(JFieldVar f1, JFieldVar f2) {
            return new EqualsBuilder()
                    .append(f1.name(), f2.name())
                    .append(f1.type().fullName(), f2.type().fullName())
                    .isEquals();
        }

        private int hashCode(Map.Entry<String, JFieldVar> entry) {
            JFieldVar field = entry.getValue();
            return new HashCodeBuilder()
                    .append(field.name())
                    .append(field.type().fullName())
                    .toHashCode();
        }

        @Override
        public int hashCode() {
            return this.fields.entrySet()
                    .stream()
                    .map(this::hashCode)
                    .reduce((h1, h2) -> new HashCodeBuilder().append(h1).append(h2).toHashCode())
                    .orElse(0);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FieldClass)) {
                return false;
            }
            FieldClass that = (FieldClass) obj;
            Map<String, JFieldVar> thisFields = this.fields;
            Map<String, JFieldVar> thatFields = that.fields;
            Set<String> thisKeys = thisFields.keySet();
            Set<String> thatKeys = thatFields.keySet();
            if (thisKeys.equals(thatKeys)) {
                return thisKeys.stream().allMatch(key -> {
                    JFieldVar thisField = thisFields.get(key);
                    JFieldVar thatField = thatFields.get(key);
                    return fieldEquals(thisField, thatField);
                });
            }
            return false;
        }
    }
}
