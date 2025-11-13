package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaReturn extends IJavaTerminalStatement {
   @Override
   IJavaExpression getExpression();

   @Override
   void setExpression(IJavaExpression var1);

   boolean returnsVoid();

   IJavaReturn duplicate();
}
