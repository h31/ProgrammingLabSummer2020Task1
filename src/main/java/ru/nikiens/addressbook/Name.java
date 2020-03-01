package ru.nikiens.addressbook;

import java.util.Objects;

class Name {
    enum Format {
        SINGLE("(?Ui:([\\w-])+)"),
        MULTIPLE("(?Ui:([\\w-])+(\\s[\\w-]*)*)");

        final String regex;

        Format(String regex) {
            this.regex = regex;
        }

        @Override
        public String toString() {
            return regex;
        }
    }

    private String name;

    Name(String name) {
        if (!name.matches(Format.SINGLE.toString())) throw new IllegalArgumentException();

        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name1 = (Name) o;

        return Objects.equals(getName(), name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
