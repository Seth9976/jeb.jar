package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICTuple extends ICLeftExpression {
   int getCountOfEntries();

   ICExpression getEntry(int var1);

   List getEntries();

   void setEntries(List var1);

   ICTuple duplicate();
}
