package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;
import java.util.Deque;

public class wJ {
   public static final hg pC = hg.pC(257);
   public static final hg A = hg.pC(pC.getFlags() | 65536);
   public static final hg kS = hg.pC(A.getFlags() | 131072);
   public static final hg wS = hg.pC(pC.getFlags() | 524288);
   public static final hg UT = hg.pC(A.getFlags() | 524288);
   public static final hg E = hg.pC(pC.getFlags() | 524288);
   public static final hg sY = hg.pC(A.getFlags() | 524288);
   public static final hg ys = hg.pC(A.getFlags() | 262144);
   public static final hg ld = hg.pC(513);
   public static final hg gp = hg.pC(769);
   private static final Hu z = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, DirectEncodedMemoryArea.get(0, 24).shift(2));
   private static final Hu Ek = new IV(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32,
      2097152,
      new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(0, 24), DirectEncodedMemoryArea.get(24, 1), VirtualEncodedMemoryArea._0)
   );
   public static final tz[] oT = new tz[]{new UC("B", pC, z), new UC("BL", A, z), new UC("BLX", kS, Ek), new UC("BLX", kS, Ek)};
   public static final tz[] fI = new tz[]{null, new UC("BX", wS, wT.pC), new UC("BXJ", pC, wT.pC).ys(), new UC("BLX", UT, wT.pC).ys()};
   public static final tz[] WR = new tz[]{new UC("BX", E, LY.sY), new UC("BLX", sY, LY.sY).ys()};
   private static final Hu hK = new IV(
      2097152, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(9, 1), DirectEncodedMemoryArea.get(3, 5), VirtualEncodedMemoryArea._0)
   );
   public static final tz NS = new UC("CBNZ", pC, LY.pC, hK).pC(new UC.Av(2097152));
   public static final tz vP = new UC("CBZ", pC, LY.pC, hK).pC(new UC.Av(1048576));
   private static final IV Er = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, DirectEncodedMemoryArea.get(0, 8).shift(1));
   public static final tz xC = new UC("B", pC, Er).pC(new UC.Av(DirectEncodedMemoryArea.get(8, 4)));
   private static final IV FE = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, DirectEncodedMemoryArea.get(0, 11).shift(1));
   public static final tz ED = new UC("B", pC, FE);
   private static final ji Aj = new sD();
   private static final IV EX = new IV(
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
   public static final tz Sc = new UC("B", pC, EX).pC(Aj).pC(new UC.Av(DirectEncodedMemoryArea.getThumb2(6, 1, 4)));
   private static final ji LM = new nf();
   private static final IEncodedMemoryArea mv = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(26, 1),
      TN.A(TN.A(DirectEncodedMemoryArea.get(13, 1), DirectEncodedMemoryArea.get(26, 1))),
      TN.A(TN.A(DirectEncodedMemoryArea.get(11, 1), DirectEncodedMemoryArea.get(26, 1))),
      DirectEncodedMemoryArea.get(16, 10),
      DirectEncodedMemoryArea.get(0, 11),
      VirtualEncodedMemoryArea._0
   );
   private static final IV sO = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, mv);
   private static final IEncodedMemoryArea os = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(26, 1),
      TN.A(TN.A(DirectEncodedMemoryArea.get(13, 1), DirectEncodedMemoryArea.get(26, 1))),
      TN.A(TN.A(DirectEncodedMemoryArea.get(11, 1), DirectEncodedMemoryArea.get(26, 1))),
      DirectEncodedMemoryArea.get(16, 10),
      DirectEncodedMemoryArea.get(1, 10),
      VirtualEncodedMemoryArea._00
   );
   private static final IV Cu = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 35651584, os);
   public static final tz ah = new UC("B", pC, sO).pC(LM);
   public static final tz eP = new UC("BLX", ys, Cu);
   public static final tz UO = new UC("BL", A, sO);
   public static final tz Ab = new UC("BXJ", pC, wT.ys).ys();
   private static final Hu hZ = new wJ.Av();
   public static final tz[] rl = new tz[]{new UC("TBB", ld, hZ), new UC("TBH", gp, hZ)};

   public static tz pC(byte[] var0) {
      byte var10000 = var0[1];
      Deque var1 = uV.A(var0);
      StringBuilder var2 = new StringBuilder();

      while (!var1.isEmpty()) {
         boolean var3 = (Boolean)var1.pop();
         var2.append((char)(var3 ? 'T' : 'E'));
      }

      ji.K var4 = var2.length() == 0 ? null : new ji.K(var2);
      return new UC("IT", hg.pC(1024), Ag.pC).pC(var4);
   }

   private static class Av implements Hu {
      @Override
      public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
         Yg[] var3 = new Yg[]{wT.ys.buildOperand(var1, var2), Dj.sY.buildOperand(var1, var2)};
         return new KH(var3[0], var3[1], false, true, var2);
      }

      @Override
      public String kS(byte[] var1) {
         int var2 = DirectEncodedMemoryArea.get(0, 4).decodeInt(var1);
         return var2 == 15 ? "Invalid Rm value" : Hu.super.kS(var1);
      }
   }
}
