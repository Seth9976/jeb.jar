package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaWhile extends IJavaCompound {
   IJavaPredicate getPredicate();

   void setPredicate(IJavaPredicate var1);

   IJavaBlock getBody();

   void setBody(IJavaBlock var1);

   IJavaWhile duplicate();
}
