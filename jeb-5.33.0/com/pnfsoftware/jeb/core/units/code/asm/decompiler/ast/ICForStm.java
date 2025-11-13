package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICForStm extends ICGenericLoop {
   ICStatement getPreStatement();

   void setPreStatement(ICStatement var1);

   ICStatement getPostStatement();

   void setPostStatement(ICStatement var1);

   ICForStm duplicate();
}
