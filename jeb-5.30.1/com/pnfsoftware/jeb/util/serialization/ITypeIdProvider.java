package com.pnfsoftware.jeb.util.serialization;

import java.util.Map;

public interface ITypeIdProvider {
   int getId(Class var1);

   Class getType(int var1);

   Map getMap();

   Map getReverseMap();

   void addAll(ITypeIdProvider var1);
}
