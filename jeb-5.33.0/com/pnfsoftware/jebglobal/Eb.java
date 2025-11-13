package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class Eb {
   private static final String[] FE = new String[]{
      "#0x0", "OSHLD", "OSHST", "OSH", "#0x4", "NSHLD", "NSHST", "NSH", "#0x8", "ISHLD", "ISHST", "ISH", "#0xc", "LD", "ST", "SY"
   };
   private static final String[] Aj = new String[]{
      "#0x0", "#0x1", "#0x2", "#0x3", "#0x4", "#0x5", "#0x6", "#0x7", "#0x8", "#0x9", "#0xa", "#0xb", "#0xc", "#0xd", "#0xe", "SY"
   };
   private static final String[] EX = new String[]{"OSHnXS", "NSHnXS", "ISHnXS", "SYnXS"};
   public static final Ag pC = new Ag(FE, DirectEncodedMemoryArea.get(0, 4));
   public static final Ag A = new Ag(Aj, DirectEncodedMemoryArea.get(0, 4));
   public static final Ag kS = new Ag(FE, DirectEncodedMemoryArea.get(8, 4));
   public static final Ag wS = new Ag(Ll.Av.oT, Aj, DirectEncodedMemoryArea.get(8, 4));
   public static final Ag UT = new Ag(EX, DirectEncodedMemoryArea.get(10, 2));
   public static final Hu E = new VP("CSYNC");
   public static final Hu sY = new VP("DSYNC");
   public static final tz ys = new UC("NOP").pC();
   public static final tz ld = new UC("HINT", IV.sO);
   public static final tz gp = new UC("HINT", IV.E);
   public static final tz oT = new UC("HINT", IV.sO).pC(ji.rl);
   public static final tz[] fI = new tz[]{
      new UC(0, "NOP"), new UC(1, "YIELD"), new UC(2, "WFE"), new UC(3, "WFI"), new UC(4, "SEV"), new UC(5, "SEVL"), null, null
   };
   public static final tz[] WR = new tz[]{new UC("ESB").pC(cT.WR), null, new UC("TSB", E).pC(cT.WR), null, new UC("CSDB").pC(cT.WR), null, null, null};
   public static final tz NS = new UC("DBG", IV.UT);
   public static final tz vP = new UC("HINT", new IV(DirectEncodedMemoryArea.get(5, 7)));
   public static final tz[] xC = fI;
   public static final tz[] ED = new tz[]{
      new UC("NOP").pC(ji.rl),
      new UC("YIELD").pC(ji.rl),
      new UC("WFE").pC(ji.rl),
      new UC("WFI").pC(ji.rl),
      new UC("SEV").pC(ji.rl),
      new UC("SEVL").pC(ji.rl),
      null,
      null
   };
   public static final tz[] Sc = new tz[]{
      new UC("ESB").pC(ji.z).pC(), null, new UC("TSB", E).pC(ji.z).pC(), null, new UC("CSDB").pC(ji.z).pC(), null, null, null
   };
   private static final String[] LM = new String[]{"", "c", "j", "jc"};
   private static final Hu mv = new Ag(LM, DirectEncodedMemoryArea.get(6, 2));
   private static final tz sO = new UC("BTI", mv).pC(Le.Aj);
   public static final tz[][] ah = new tz[][]{
      fI,
      {new UC("PACIA1716").pC(Le.rl), null, new UC("PACIB1716").pC(Le.rl), null, new UC("AUTIA1716").pC(Le.rl), null, new UC("AUTIB1716").pC(Le.rl)},
      {
            new UC("ESB").pC(Le.ah),
            new UC("PSB", E).pC(Le.eP),
            new UC("TSB", E).pC(Le.UO),
            new UC("GCSB", sY).pC(Le.OI),
            new UC("CSDB"),
            null,
            new UC("CLRBHB").pC(Le.hZ),
            null
      },
      {
            new UC("PACIAZ").pC(Le.rl),
            new UC("PACIASP").pC(Le.rl),
            new UC("PACIBZ").pC(Le.rl),
            new UC("PACIBSP").pC(Le.rl),
            new UC("AUTIAZ").pC(Le.rl),
            new UC("AUTIASP").pC(Le.rl),
            new UC("AUTIBZ").pC(Le.rl),
            new UC("AUTIBSP").pC(Le.rl)
      },
      {sO, null, sO, null, sO, null, sO, null},
      {new UC("CHKFEAT", sQ.ck).pC(Le.pF)}
   };
   public static final tz eP = new UC("DGH").pC(Le.Ab);
   public static final tz UO = new UC("XPACLRI").pC(Le.rl);
   public static final tz[] Ab = new tz[]{null, null, new UC("CLREX"), null, new UC("DSB", pC), new UC("DMB", pC), new UC("ISB", A), new UC("SB")};
   public static final tz[] rl = new tz[]{null, new UC("CLREX"), null, null, new UC("DSB", pC), new UC("DMB", pC), new UC("ISB", A), new UC("SB")};
   public static final tz z = new UC("SSBB");
   public static final tz Ek = new UC("PSSBB");
   private static final ZW os = new ZW(DirectEncodedMemoryArea.get(8, 2), 1L, 1L, 193514046488576L, 1L);
   public static final tz[] hK = new tz[]{
      null,
      new UC("DSB", UT).pC(os),
      new UC("CLREX", new IV(268435456, 15, DirectEncodedMemoryArea.get(8, 4))),
      new UC("TCOMMIT").pC(Le.UH),
      new UC("DSB", kS),
      new UC("DMB", kS),
      new UC("ISB", wS),
      new UC("SB").pC(Le.ZN)
   };
   public static final tz[] Er = new tz[]{new UC("WFET", sQ.A).pC(Le.VD), new UC("WFIT", sQ.A).pC(Le.VD)};

   public static tz pC(byte[] var0, int var1) {
      int var2 = (var0[3] & 240) >>> 4;
      int var3 = var0[3] & 15;
      if (var2 == 15) {
         return NS;
      } else if (var2 == 0) {
         return (tz)ArrayUtil.getSafe(var1 == 16 ? ED : fI, var3, pC(var1));
      } else {
         return var2 == 1 && (var1 == 16 || (var0[0] & 240) == 224) ? (tz)ArrayUtil.getSafe(var1 == 16 ? Sc : WR, var3, pC(var1)) : pC(var1);
      }
   }

   private static tz pC(int var0) {
      return var0 == 16 ? oT : ld;
   }

   public static tz A(byte[] var0, int var1) throws ProcessorException {
      int var2 = (var0[3] & 240) >>> 4;
      if (var2 <= 7 && (var2 != 1 || (var0[3] & 15) == 15) && (var2 != 7 || (var0[3] & 15) == 0)) {
         if (var2 == 4) {
            int var3 = var0[3] & 15;
            if (var3 == 0) {
               return z;
            }

            if (var3 == 4) {
               return Ek;
            }
         }

         return var1 == 16 ? Ab[var2] : rl[var2];
      } else {
         return UC.pC(var0, "Thumb-2/Miscellaneous System");
      }
   }
}
