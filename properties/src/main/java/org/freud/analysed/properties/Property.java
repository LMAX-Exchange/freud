package org.freud.analysed.properties;

public final class Property {
    private final String key;
    private final String value;

    public Property(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Property property = (Property) o;

        if (key != null ? !key.equals(property.key) : property.key != null) return false;
        if (value != null ? !value.equals(property.value) : property.value != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return key + '=' + value;
    }
}
