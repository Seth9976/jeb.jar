package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Lm {
   private static final de LK = new de.qV(de.EE.q, de.EE.RF);
   public static final Je[] q = new Je[]{
      new Qg("REV", Pc.nf, Pc.Dw).q(QI.Dw),
      new Qg("REV16", Pc.nf, Pc.Dw).q(QI.Dw),
      new Qg("RBIT", Pc.nf, Pc.Dw).q(QI.Dw),
      new Qg("REVSH", Pc.nf, Pc.Dw).q(QI.Dw)
   };
   public static final Je[] RF = new Je[]{
      new Qg("REV", iv.Dw, iv.Uv), new Qg("REV16", iv.Dw, iv.Uv), new Qg("HLT", go.CE).q().q(oY.oW), new Qg("REVSH", iv.Dw, iv.Uv)
   };
   private static final YB io = new qE(4, 0, 16);
   public static final Je[][] xK = new Je[][]{
      Lx.Dw,
      {
            new Qg("REV", Pc.gO, Pc.Dw).q(QI.Dw).q(LK).q(io),
            new Qg("REV16", Pc.gO, Pc.Dw).q(QI.Dw).q(LK).q(io),
            new Qg("RBIT", Pc.gO, Pc.Dw).q(QI.Dw).q(io),
            new Qg("REVSH", Pc.gO, Pc.Dw).q(QI.Dw).q(LK).q(io)
      },
      {new Qg("SEL", Pc.gO, Pc.lm, Pc.Dw).q(QI.Uv)},
      {new Qg("CLZ", Pc.gO, Pc.Dw).q(QI.Dw).q(io)},
      Cf.RF,
      Cf.xK
   };
   public static final Je Dw = new Qg("MOVT", Pc.nf, go.AB).nf();
   private static final IEncodedMemoryArea qa = DirectEncodedMemoryArea.get(7, 5);
   private static final go Hk = new go(qa);
   private static final go Me = new Lm.eo(1048576, DirectEncodedMemoryArea.get(16, 5), qa);
   public static final Je Uv = new Qg("BFI", Pc.nf, Pc.Dw, Hk, Me).nf();
   public static final Je oW = new Qg("BFC", Pc.nf, Hk, Me).nf();
   public static final Je gO = new Qg("SBFX", Pc.nf, Pc.Dw, Hk, go.KT).q(QI.Dw);
   public static final Je nf = new Qg("UBFX", Pc.nf, Pc.Dw, Hk, go.KT).q(QI.Dw);
   public static final Je gP = new Qg("USAD8", Pc.lm, Pc.Dw, Pc.gO).q(QI.Uv);
   public static final Je za = new Qg("USADA8", Pc.lm, Pc.Dw, Pc.gO, Pc.nf);
   private static final IEncodedMemoryArea PV = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(6, 2));
   private static final go oQ = new go(PV);
   private static final go xW = new Lm.eo(1048576, DirectEncodedMemoryArea.get(0, 5), PV);
   public static final Je lm = new Qg("BFC", Pc.gO, oQ, xW).nf();
   public static final Je[] zz = new Je[]{
      new Qg("SBFX", Pc.gO, Pc.lm, oQ, go.oQ).q(QI.Dw), new Qg("BFI", Pc.gO, Pc.lm, oQ, xW).nf(), new Qg("UBFX", Pc.gO, Pc.lm, oQ, go.oQ).q(QI.Dw), null
   };
   public static final Je JY = new Qg("SEL", Pc.nf, Pc.lm, Pc.Dw).q(QI.Uv);
   public static final Je HF = new Qg("CLZ", Pc.nf, Pc.Dw).q(QI.Dw);

   private static class eo extends go {
      private IEncodedMemoryArea WI;

      public eo(int var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
         super(var1, var2);
         this.WI = var3;
      }

      @Override
      protected long getValue(byte[] var1, int var2) throws ProcessorException {
         long var3 = k.RF(var1, this.getMemoryArea());
         long var5 = k.RF(var1, this.WI);
         if (var3 >= var5) {
            return var3 - var5;
         } else {
            throw new ProcessorException("Unpredictable BFC Instruction: Illegal width");
         }
      }
   }
}
