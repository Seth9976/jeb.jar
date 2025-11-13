package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaFieldFactory {
   IJavaField get(String var1);

   IJavaField create(String var1);
}
