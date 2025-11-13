package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class afn extends adn {
   @Override
   public CElementType getElementType() {
      return null;
   }

   public afn A() {
      return this;
   }

   @Override
   public void generate(COutputSink var1) {
   }
}
