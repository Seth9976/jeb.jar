package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IJavaCatchBlock {
   IJavaType getType();

   void setType(IJavaType var1);

   List getCaughtTypes();

   List getAdditionalCaughtTypes();

   void addTypes(Collection var1);

   void addType(IJavaType var1);

   boolean canCatch(String var1);

   void setIdentifier(IJavaIdentifier var1);

   IJavaIdentifier getIdentifier();

   void setDefifinition(IJavaDefinition var1);

   IJavaDefinition getDefinition();

   void setBlock(IJavaBlock var1);

   IJavaBlock getBlock();
}
