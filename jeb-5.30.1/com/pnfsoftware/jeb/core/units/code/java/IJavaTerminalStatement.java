package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaTerminalStatement extends IJavaStatement {
   IJavaExpression getExpression();

   void setExpression(IJavaExpression var1);

   IJavaTerminalStatement duplicate();
}
