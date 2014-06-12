package org.shellty.compiler.semantic.Meta;

public abstract class BasicMetaType {
    private String value = "";

    public BasicMetaType(String value) {
        this.value = value;
    }

    public BasicMetaType() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        return this.getClass() == other.getClass();
    }

}

