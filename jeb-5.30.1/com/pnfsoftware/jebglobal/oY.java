package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

public class oY {
   private static final String[] Ef = new String[]{
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
   public static final wJ q = new wJ(Ef, DirectEncodedMemoryArea.get(0, 5));
   public static final OQ RF = OQ.q(268437249);
   public static final OQ xK = OQ.q(536872705);
   public static final OQ Dw = OQ.q(805308161);
   public static final OQ Uv = OQ.q(1073743617);
   public static final OQ oW = OQ.q(2816);
   public static final Je[] gO = new Je[]{
      new Qg("HLT", go.Wx).q(oW).q(QI.JY), new Qg("BKPT", go.Wx).q(oW).q(QI.JY), new Qg("HVC", go.Wx).q(oW).q(QI.JY), new Qg("SMC", go.zz).q(oW)
   };
   public static final Je nf = new Qg("ERET");
   public static final Je gP = new Qg("SVC", new go(DirectEncodedMemoryArea.get(0, 24)));
   public static final Je[] za = new Je[]{
      null,
      new Qg(1, "RFEDA", Pc.lm).nf().q(xK),
      new Qg(2, "SRSDA", Pc.Hk, q),
      null,
      null,
      new Qg(5, "RFEIA", Pc.lm).nf().q(RF),
      new Qg(6, "SRSIA", Pc.Hk, q),
      null,
      null,
      new Qg(9, "RFEDB", Pc.lm).nf().q(Dw),
      new Qg(10, "SRSDB", Pc.Hk, q),
      null,
      null,
      new Qg(13, "RFEIB", Pc.lm).nf().q(Uv),
      new Qg(14, "SRSIB", Pc.Hk, q),
      null,
      null,
      new Qg(17, "RFEDA", Pc.zz).nf().q(xK),
      new Qg(18, "SRSDA", Pc.Me, q),
      null,
      null,
      new Qg(21, "RFEIA", Pc.zz).nf().q(RF),
      new Qg(22, "SRSIA", Pc.Me, q),
      null,
      null,
      new Qg(25, "RFEDB", Pc.zz).nf().q(Dw),
      new Qg(26, "SRSDB", Pc.Me, q),
      null,
      null,
      new Qg(29, "RFEIB", Pc.zz).nf().q(Uv),
      new Qg(30, "SRSIB", Pc.Me, q),
      null
   };
   public static final Je[] lm = new Je[]{
      new Qg(0, "SRSDB", Pc.Hk, q),
      new Qg(1, "SRSDB", Pc.Me, q),
      new Qg(2, "RFEDB", Pc.lm).nf().q(Dw),
      new Qg(3, "RFEDB", Pc.zz).nf().q(Dw),
      new Qg(4, "SRSIA", Pc.Hk, q),
      new Qg(5, "SRSIA", Pc.Me, q),
      new Qg(6, "RFEIA", Pc.lm).nf().q(RF),
      new Qg(7, "RFEIA", Pc.zz).nf().q(RF)
   };
   public static final Je zz = new Qg("UDF", go.Wx).q(oW);
   public static final Je JY = new Qg("BKPT", go.IN).q(oW).q();
   public static final Je HF = new Qg("UDF", go.IN).q(oW);
   public static final Je LK = new Qg("SVC", go.IN).q(oW);
   private static final de cC = new HD();
   public static final Je[] io = new Je[]{new Qg("HVC", go.AB).q(oW).q(), null, new Qg("SMC", go.qa).q(oW), new Qg("UDF", go.AB).q(oW).q(cC)};
   public static final Je[] qa = new Je[]{null, new Qg("DCPS1"), new Qg("DCPS2"), new Qg("DCPS3")};
   public static final Je Hk = new Qg("ERET").q(oW);
   private static final go sH = new go(DirectEncodedMemoryArea.get(5, 16));
   public static final Je[] Me = new Je[]{null, new Qg("SVC", sH).q(oW), new Qg("HVC", sH).q(oW), new Qg("SMC", sH).q(oW)};
   public static final Je PV = new Qg("BRK", sH).q(oW);
   public static final Je oQ = new Qg("HLT", sH).q(oW);
   public static final Je xW = new Qg("TCANCEL", sH).q(QJ.Kn);
   private static final go CE = new go(268435456, DirectEncodedMemoryArea.get(5, 16));
   public static final Je[] KT = new Je[]{null, new Qg("DCPS1", CE), new Qg("DCPS2", CE), new Qg("DCPS3", CE)};
   public static final Je Gf = new Qg("UDF", go.ZT).q(oW);

   public static Je q(byte[] var0) {
      return za[HS.xK(var0, 4) | HS.q(var0, 3) | (var0[1] & 192) >>> 5 | HS.Dw(var0, 0)];
   }
}
