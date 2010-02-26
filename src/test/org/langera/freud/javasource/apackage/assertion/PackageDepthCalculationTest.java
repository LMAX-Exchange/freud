package org.langera.freud.javasource.apackage.assertion;

import org.junit.Assert;
import org.junit.Test;
import org.langera.freud.javasource.apackage.PackageDeclaration;

public class PackageDepthCalculationTest 
{
    @Test
    public void shouldCalculateDepth()
    {
        int depth = PackageDepthCalculation.getInstance().analyse(new PackageDeclaration()
        {
            public String[] getPackagePath()
            {
                return new String[] {"a", "b", "c" };
            }

            public String getPackagePathAsString()
            {
                return "a.b.c";
            }
        });

        Assert.assertEquals(3, depth);                
    }

    @Test
    public void shouldCalculateDepthWhenNoPackageExists()
    {
        int depth = PackageDepthCalculation.getInstance().analyse(new PackageDeclaration()
        {
            public String[] getPackagePath()
            {
                return new String[] {};
            }

            public String getPackagePathAsString()
            {
                return "";
            }
        });

        Assert.assertEquals(0, depth);
    }
}
