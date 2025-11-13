package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICGenericLoop extends ICGenericBreakable {
   void setPredicate(ICPredicate var1);

   ICPredicate getPredicate();

   void setBody(ICBlock var1);

   ICBlock getBody();

   ICGenericLoop duplicate();
}
