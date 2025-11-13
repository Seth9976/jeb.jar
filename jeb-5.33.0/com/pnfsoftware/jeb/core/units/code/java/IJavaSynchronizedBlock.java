package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaSynchronizedBlock extends IJavaCompound {
   IJavaExpression getLock();

   void setLock(IJavaExpression var1);

   IJavaBlock getBody();

   void setBody(IJavaBlock var1);
}
