package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

public class bb {
   private static final String[] Ek = new String[]{
      "#0",
      "#1",
      "#2",
      "#3",
      "#4",
      "#5",
      "#6",
      "#7",
      "#8",
      "#9",
      "#10",
      "#11",
      "#12",
      "#13",
      "#14",
      "#15",
      "M32_User",
      "M32_FIQ",
      "M32_IRQ",
      "M32_Svc",
      "10100",
      "10101",
      "M32_Monitor",
      "M32_Abort",
      "11000",
      "11001",
      "M32_Hyp",
      "M32_Undef",
      "11100",
      "11101",
      "11110",
      "M32_System"
   };
   public static final Ag pC = new Ag(Ek, DirectEncodedMemoryArea.get(0, 5));
   public static final hg A = hg.pC(268437249);
   public static final hg kS = hg.pC(536872705);
   public static final hg wS = hg.pC(805308161);
   public static final hg UT = hg.pC(1073743617);
   public static final hg E = hg.pC(2816);
   public static final tz[] sY = new tz[]{
      new UC("HLT", IV.OB).pC(E).pC(cT.WR), new UC("BKPT", IV.OB).pC(E).pC(cT.WR), new UC("HVC", IV.OB).pC(E).pC(cT.WR), new UC("SMC", IV.UT).pC(E)
   };
   public static final tz ys = new UC("ERET");
   public static final tz ld = new UC("SVC", new IV(DirectEncodedMemoryArea.get(0, 24)));
   public static final tz[] gp = new tz[]{
      null,
      new UC(1, "RFEDA", wT.ys).ys().pC(kS),
      new UC(2, "SRSDA", wT.vP, pC),
      null,
      null,
      new UC(5, "RFEIA", wT.ys).ys().pC(A),
      new UC(6, "SRSIA", wT.vP, pC),
      null,
      null,
      new UC(9, "RFEDB", wT.ys).ys().pC(wS),
      new UC(10, "SRSDB", wT.vP, pC),
      null,
      null,
      new UC(13, "RFEIB", wT.ys).ys().pC(UT),
      new UC(14, "SRSIB", wT.vP, pC),
      null,
      null,
      new UC(17, "RFEDA", wT.ld).ys().pC(kS),
      new UC(18, "SRSDA", wT.xC, pC),
      null,
      null,
      new UC(21, "RFEIA", wT.ld).ys().pC(A),
      new UC(22, "SRSIA", wT.xC, pC),
      null,
      null,
      new UC(25, "RFEDB", wT.ld).ys().pC(wS),
      new UC(26, "SRSDB", wT.xC, pC),
      null,
      null,
      new UC(29, "RFEIB", wT.ld).ys().pC(UT),
      new UC(30, "SRSIB", wT.xC, pC),
      null
   };
   public static final tz[] oT = new tz[]{
      new UC(0, "SRSDB", wT.vP, pC),
      new UC(1, "SRSDB", wT.xC, pC),
      new UC(2, "RFEDB", wT.ys).ys().pC(wS),
      new UC(3, "RFEDB", wT.ld).ys().pC(wS),
      new UC(4, "SRSIA", wT.vP, pC),
      new UC(5, "SRSIA", wT.xC, pC),
      new UC(6, "RFEIA", wT.ys).ys().pC(A),
      new UC(7, "RFEIA", wT.ld).ys().pC(A)
   };
   public static final tz fI = new UC("UDF", IV.OB).pC(E);
   public static final tz WR = new UC("BKPT", IV.sO).pC(E).pC();
   public static final tz NS = new UC("UDF", IV.sO).pC(E);
   public static final tz vP = new UC("SVC", IV.sO).pC(E);
   private static final ji hK = new lG();
   public static final tz[] xC = new tz[]{new UC("HVC", IV.pF).pC(E).pC(), null, new UC("SMC", IV.gp).pC(E), new UC("UDF", IV.pF).pC(E).pC(hK)};
   public static final tz[] ED = new tz[]{null, new UC("DCPS1"), new UC("DCPS2"), new UC("DCPS3")};
   public static final tz Sc = new UC("ERET").pC(E);
   private static final IV Er = new IV(DirectEncodedMemoryArea.get(5, 16));
   public static final tz[] ah = new tz[]{null, new UC("SVC", Er).pC(E), new UC("HVC", Er).pC(E), new UC("SMC", Er).pC(E)};
   public static final tz eP = new UC("BRK", Er).pC(E);
   public static final tz UO = new UC("HLT", Er).pC(E);
   public static final tz Ab = new UC("TCANCEL", Er).pC(Le.UH);
   private static final IV FE = new IV(268435456, DirectEncodedMemoryArea.get(5, 16));
   public static final tz[] rl = new tz[]{null, new UC("DCPS1", FE), new UC("DCPS2", FE), new UC("DCPS3", FE)};
   public static final tz z = new UC("UDF", IV.cX).pC(E);

   public static tz pC(byte[] var0) {
      return gp[ZC.kS(var0, 4) | ZC.pC(var0, 3) | (var0[1] & 192) >>> 5 | ZC.wS(var0, 0)];
   }
}
