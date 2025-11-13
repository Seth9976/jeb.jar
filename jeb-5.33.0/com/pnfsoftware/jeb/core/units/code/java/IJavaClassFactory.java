package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaClassFactory {
   IJavaClass get(String var1);

   IJavaClass create(String var1);
}
