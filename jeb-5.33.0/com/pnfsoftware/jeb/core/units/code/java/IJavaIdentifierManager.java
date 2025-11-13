package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IJavaIdentifierManager {
   IJavaDefinition createDefinition(IJavaType var1, String var2);

   IJavaDefinition getDefinition(IJavaIdentifier var1);

   IJavaDefinition getDefinition(int var1);

   Collection getIdentifiers();

   IJavaIdentifier getIdentifier(String var1);
}
