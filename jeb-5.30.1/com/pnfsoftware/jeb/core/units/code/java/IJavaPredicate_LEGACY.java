package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaPredicate_LEGACY extends IJavaOperation {
   boolean isLitteralTrue();

   boolean isLitteralFalse();

   void reverse();

   boolean simplify();

   IJavaPredicate_LEGACY duplicate();
}
