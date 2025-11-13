package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaMethodFactory {
   IJavaMethod get(String var1);

   IJavaMethod create(String var1);
}
