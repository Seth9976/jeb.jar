package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaMonitor extends IJavaStatement {
   boolean isEnter();

   boolean isExit();

   void setEnter(boolean var1);

   IJavaExpression getLock();

   void setLock(IJavaExpression var1);

   IJavaMonitor duplicate();
}
