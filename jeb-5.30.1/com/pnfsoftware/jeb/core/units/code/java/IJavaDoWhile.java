package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaDoWhile extends IJavaCompound {
   IJavaPredicate getPredicate();

   IJavaBlock getBody();

   void setPredicate(IJavaPredicate var1);

   void setBody(IJavaBlock var1);

   IJavaDoWhile duplicate();
}
