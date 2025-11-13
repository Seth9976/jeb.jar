package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface fA extends IInstruction {
   lH q();

   ICodePointer q(IMachineContext var1) throws ProcessorException;

   CW[] RF();

   CW q(int var1);

   List q(long var1);

   byte[] xK();

   Op Dw();

   mZ Uv();

   boolean oW();

   boolean gO();

   BytesBlock getCodeBlock();

   boolean nf();

   boolean gP();

   List za();

   long lm();

   boolean zz();

   CW[] JY();
}
