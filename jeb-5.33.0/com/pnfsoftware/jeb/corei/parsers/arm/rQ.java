package com.pnfsoftware.jeb.corei.parsers.arm;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.NC;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.dW;
import com.pnfsoftware.jebglobal.zj;
import java.util.List;

@Ser
public interface rQ extends IInstruction {
   dW pC();

   ICodePointer pC(IMachineContext var1) throws ProcessorException;

   Yg[] A();

   Yg pC(int var1);

   byte[] kS();

   NC wS();

   zj UT();

   boolean E();

   boolean sY();

   BytesBlock getCodeBlock();

   boolean ys();

   boolean ld();

   List gp();

   long oT();

   boolean fI();

   Yg[] WR();
}
