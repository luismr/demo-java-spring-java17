package com.example.demo.configure;

import com.example.demo.type.Color;
import com.example.demo.type.Part;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import java.util.*;

/**
 * this code is investigation.
 */
@Component
@ConfigurationProperties(prefix = "my-app.my-module.foo")
@Validated
public class FooProperties {

    /**
     * foo name description.
     */
    @NotNull
    private String name;

    /**
     * foo max height description.
     */
    @NotNull
    private Integer maxHeight;

    /**
     * foo min height description.
     */
    @NotNull
    private Integer minHeight;

    /**
     * foo color description.
     */
    @NotNull
    private Color color;

    /**
     * foo colors description.
     */
    private List<Color> colors = new ArrayList<>();
    //private EnumSet<Color> setColors = EnumSet.noneOf(Color.class);

    /**
     * foo parts description.
     */
    private Map<Part, String> parts = new HashMap<>();

    /**
     * foo budget description.
     */
    private Long budget;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Map<Part, String> getParts() {
        return parts;
    }

    public void setParts(Map<Part, String> parts) {
        this.parts = parts;
    }

    @DeprecatedConfigurationProperty(reason = "reason not to use budget.")
    @Deprecated
    public Long getBudget() {
        return budget;
    }

    @Deprecated
    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "FooProperties{" +
                "name='" + name + '\'' +
                ", maxHeight=" + maxHeight +
                ", minHeight=" + minHeight +
                ", color=" + color +
                ", colors=" + colors +
                ", parts=" + parts +
                ", budget=" + budget +
                '}';
    }

}

