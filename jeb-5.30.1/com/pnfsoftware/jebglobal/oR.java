package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;
import java.util.Deque;

public class oR {
   public static final OQ q = OQ.q(257);
   public static final OQ RF = OQ.q(q.getFlags() | 65536);
   public static final OQ xK = OQ.q(RF.getFlags() | 131072);
   public static final OQ Dw = OQ.q(q.getFlags() | 524288);
   public static final OQ Uv = OQ.q(RF.getFlags() | 524288);
   public static final OQ oW = OQ.q(q.getFlags() | 524288);
   public static final OQ gO = OQ.q(RF.getFlags() | 524288);
   public static final OQ nf = OQ.q(RF.getFlags() | 262144);
   public static final OQ gP = OQ.q(513);
   public static final OQ za = OQ.q(769);
   private static final Ef Gf = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, DirectEncodedMemoryArea.get(0, 24).shift(2));
   private static final Ef Ef = new go(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32,
      2097152,
      new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(0, 24), DirectEncodedMemoryArea.get(24, 1), VirtualEncodedMemoryArea._0)
   );
   public static final Je[] lm = new Je[]{new Qg("B", q, Gf), new Qg("BL", RF, Gf), new Qg("BLX", xK, Ef), new Qg("BLX", xK, Ef)};
   public static final Je[] zz = new Je[]{null, new Qg("BX", Dw, Pc.Dw), new Qg("BXJ", q, Pc.Dw).nf(), new Qg("BLX", Uv, Pc.Dw).nf()};
   public static final Je[] JY = new Je[]{new Qg("BX", oW, iv.za), new Qg("BLX", gO, iv.za).nf()};
   private static final Ef cC = new go(
      2097152, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(9, 1), DirectEncodedMemoryArea.get(3, 5), VirtualEncodedMemoryArea._0)
   );
   public static final Je HF = new Qg("CBNZ", q, iv.Dw, cC).q(new Qg.eo(2097152));
   public static final Je LK = new Qg("CBZ", q, iv.Dw, cC).q(new Qg.eo(1048576));
   private static final go sH = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, DirectEncodedMemoryArea.get(0, 8).shift(1));
   public static final Je io = new Qg("B", q, sH).q(new Qg.eo(DirectEncodedMemoryArea.get(8, 4)));
   private static final go CE = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, DirectEncodedMemoryArea.get(0, 11).shift(1));
   public static final Je qa = new Qg("B", q, CE);
   private static final de wF = new NN();
   private static final go If = new go(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32,
      2097152,
      new EncodedMemoryAreaList(
         DirectEncodedMemoryArea.get(26, 1),
         DirectEncodedMemoryArea.get(11, 1),
         DirectEncodedMemoryArea.get(13, 1),
         DirectEncodedMemoryArea.get(16, 6),
         DirectEncodedMemoryArea.get(0, 11),
         VirtualEncodedMemoryArea._0
      )
   );
   public static final Je Hk = new Qg("B", q, If).q(wF).q(new Qg.eo(DirectEncodedMemoryArea.getThumb2(6, 1, 4)));
   private static final de Dz = new nk();
   private static final IEncodedMemoryArea mI = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(26, 1),
      dX.RF(dX.RF(DirectEncodedMemoryArea.get(13, 1), DirectEncodedMemoryArea.get(26, 1))),
      dX.RF(dX.RF(DirectEncodedMemoryArea.get(11, 1), DirectEncodedMemoryArea.get(26, 1))),
      DirectEncodedMemoryArea.get(16, 10),
      DirectEncodedMemoryArea.get(0, 11),
      VirtualEncodedMemoryArea._0
   );
   private static final go jq = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, mI);
   private static final IEncodedMemoryArea ui = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(26, 1),
      dX.RF(dX.RF(DirectEncodedMemoryArea.get(13, 1), DirectEncodedMemoryArea.get(26, 1))),
      dX.RF(dX.RF(DirectEncodedMemoryArea.get(11, 1), DirectEncodedMemoryArea.get(26, 1))),
      DirectEncodedMemoryArea.get(16, 10),
      DirectEncodedMemoryArea.get(1, 10),
      VirtualEncodedMemoryArea._00
   );
   private static final go TX = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 35651584, ui);
   public static final Je Me = new Qg("B", q, jq).q(Dz);
   public static final Je PV = new Qg("BLX", nf, TX);
   public static final Je oQ = new Qg("BL", RF, jq);
   public static final Je xW = new Qg("BXJ", q, Pc.lm).nf();
   private static final Ef Rr = new oR.eo();
   public static final Je[] KT = new Je[]{new Qg("TBB", gP, Rr), new Qg("TBH", za, Rr)};

   public static Je q(byte[] var0) {
      byte var10000 = var0[1];
      Deque var1 = Lf.RF(var0);
      StringBuilder var2 = new StringBuilder();

      while (!var1.isEmpty()) {
         boolean var3 = (Boolean)var1.pop();
         var2.append((char)(var3 ? 'T' : 'E'));
      }

      de.ej var4 = var2.length() == 0 ? null : new de.ej(var2);
      return new Qg("IT", OQ.q(1024), wJ.q).q(var4);
   }

   private static class eo implements Ef {
      @Override
      public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
         CW[] var3 = new CW[]{Pc.lm.buildOperand(var1, var2), cn.Me.buildOperand(var1, var2)};
         return new wh(var3[0], var3[1], false, true, var2);
      }

      @Override
      public String xK(byte[] var1) {
         int var2 = DirectEncodedMemoryArea.get(0, 4).decodeInt(var1);
         return var2 == 15 ? "Invalid Rm value" : Ef.super.xK(var1);
      }
   }
}
