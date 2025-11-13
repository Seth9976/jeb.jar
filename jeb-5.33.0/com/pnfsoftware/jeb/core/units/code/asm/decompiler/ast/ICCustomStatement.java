package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICCustomStatement extends ICStatement {
   long getNativeAddress();

   void setCommandName(String var1);

   String getCommandName();

   void setInputElements(List var1);

   List getInputElements();

   void setOutputElements(List var1);

   List getOutputElements();

   ICCustomStatement duplicate();
}
