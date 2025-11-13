package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class iC {
   private static final String[] CE = new String[]{
      "#0x0", "OSHLD", "OSHST", "OSH", "#0x4", "NSHLD", "NSHST", "NSH", "#0x8", "ISHLD", "ISHST", "ISH", "#0xc", "LD", "ST", "SY"
   };
   private static final String[] wF = new String[]{
      "#0x0", "#0x1", "#0x2", "#0x3", "#0x4", "#0x5", "#0x6", "#0x7", "#0x8", "#0x9", "#0xa", "#0xb", "#0xc", "#0xd", "#0xe", "SY"
   };
   private static final String[] If = new String[]{"OSHnXS", "NSHnXS", "ISHnXS", "SYnXS"};
   public static final wJ q = new wJ(CE, DirectEncodedMemoryArea.get(0, 4));
   public static final wJ RF = new wJ(wF, DirectEncodedMemoryArea.get(0, 4));
   public static final wJ xK = new wJ(CE, DirectEncodedMemoryArea.get(8, 4));
   public static final wJ Dw = new wJ(jD.eo.lm, wF, DirectEncodedMemoryArea.get(8, 4));
   public static final wJ Uv = new wJ(If, DirectEncodedMemoryArea.get(10, 2));
   public static final Ef oW = new Cx("CSYNC");
   public static final Ef gO = new Cx("DSYNC");
   public static final Je nf = new Qg("NOP").q();
   public static final Je gP = new Qg("HINT", go.IN);
   public static final Je za = new Qg("HINT", go.JY);
   public static final Je lm = new Qg("HINT", go.IN).q(de.KT);
   public static final Je[] zz = new Je[]{
      new Qg(0, "NOP"), new Qg(1, "YIELD"), new Qg(2, "WFE"), new Qg(3, "WFI"), new Qg(4, "SEV"), new Qg(5, "SEVL"), null, null
   };
   public static final Je[] JY = new Je[]{new Qg("ESB").q(QI.JY), null, new Qg("TSB", oW).q(QI.JY), null, new Qg("CSDB").q(QI.JY), null, null, null};
   public static final Je HF = new Qg("DBG", go.zz);
   public static final Je LK = new Qg("HINT", new go(DirectEncodedMemoryArea.get(5, 7)));
   public static final Je[] io = zz;
   public static final Je[] qa = new Je[]{
      new Qg("NOP").q(de.KT),
      new Qg("YIELD").q(de.KT),
      new Qg("WFE").q(de.KT),
      new Qg("WFI").q(de.KT),
      new Qg("SEV").q(de.KT),
      new Qg("SEVL").q(de.KT),
      null,
      null
   };
   public static final Je[] Hk = new Je[]{
      new Qg("ESB").q(de.Gf).q(), null, new Qg("TSB", oW).q(de.Gf).q(), null, new Qg("CSDB").q(de.Gf).q(), null, null, null
   };
   private static final String[] Dz = new String[]{"", "c", "j", "jc"};
   private static final Ef mI = new wJ(Dz, DirectEncodedMemoryArea.get(6, 2));
   private static final Je jq = new Qg("BTI", mI).q(QJ.PY);
   public static final Je[][] Me = new Je[][]{
      zz,
      {new Qg("PACIA1716").q(QJ.IY), null, new Qg("PACIB1716").q(QJ.IY), null, new Qg("AUTIA1716").q(QJ.IY), null, new Qg("AUTIB1716").q(QJ.IY)},
      {
            new Qg("ESB").q(QJ.kf),
            new Qg("PSB", oW).q(QJ.GM),
            new Qg("TSB", oW).q(QJ.TQ),
            new Qg("GCSB", gO).q(QJ.Bs),
            new Qg("CSDB"),
            null,
            new Qg("CLRBHB").q(QJ.vC),
            null
      },
      {
            new Qg("PACIAZ").q(QJ.IY),
            new Qg("PACIASP").q(QJ.IY),
            new Qg("PACIBZ").q(QJ.IY),
            new Qg("PACIBSP").q(QJ.IY),
            new Qg("AUTIAZ").q(QJ.IY),
            new Qg("AUTIASP").q(QJ.IY),
            new Qg("AUTIBZ").q(QJ.IY),
            new Qg("AUTIBSP").q(QJ.IY)
      },
      {jq, null, jq, null, jq, null, jq, null},
      {new Qg("CHKFEAT", YH.CY).q(QJ.fq)}
   };
   public static final Je PV = new Qg("DGH").q(QJ.Yw);
   public static final Je oQ = new Qg("XPACLRI").q(QJ.IY);
   public static final Je[] xW = new Je[]{null, null, new Qg("CLREX"), null, new Qg("DSB", q), new Qg("DMB", q), new Qg("ISB", RF), new Qg("SB")};
   public static final Je[] KT = new Je[]{null, new Qg("CLREX"), null, null, new Qg("DSB", q), new Qg("DMB", q), new Qg("ISB", RF), new Qg("SB")};
   public static final Je Gf = new Qg("SSBB");
   public static final Je Ef = new Qg("PSSBB");
   private static final dD ui = new dD(DirectEncodedMemoryArea.get(8, 2), 1L, 1L, 193514046488576L, 1L);
   public static final Je[] cC = new Je[]{
      null,
      new Qg("DSB", Uv).q(ui),
      new Qg("CLREX", new go(268435456, 15, DirectEncodedMemoryArea.get(8, 4))),
      new Qg("TCOMMIT").q(QJ.Kn),
      new Qg("DSB", xK),
      new Qg("DMB", xK),
      new Qg("ISB", Dw),
      new Qg("SB").q(QJ.ZU)
   };
   public static final Je[] sH = new Je[]{new Qg("WFET", YH.Uv).q(QJ.vh), new Qg("WFIT", YH.Uv).q(QJ.vh)};

   public static Je q(byte[] var0, int var1) {
      int var2 = (var0[3] & 240) >>> 4;
      int var3 = var0[3] & 15;
      if (var2 == 15) {
         return HF;
      } else if (var2 == 0) {
         return (Je)ArrayUtil.getSafe(var1 == 16 ? qa : zz, var3, q(var1));
      } else {
         return var2 == 1 && (var1 == 16 || (var0[0] & 240) == 224) ? (Je)ArrayUtil.getSafe(var1 == 16 ? Hk : JY, var3, q(var1)) : q(var1);
      }
   }

   private static Je q(int var0) {
      return var0 == 16 ? lm : gP;
   }

   public static Je RF(byte[] var0, int var1) throws ProcessorException {
      int var2 = (var0[3] & 240) >>> 4;
      if (var2 <= 7 && (var2 != 1 || (var0[3] & 15) == 15) && (var2 != 7 || (var0[3] & 15) == 0)) {
         if (var2 == 4) {
            int var3 = var0[3] & 15;
            if (var3 == 0) {
               return Gf;
            }

            if (var3 == 4) {
               return Ef;
            }
         }

         return var1 == 16 ? xW[var2] : KT[var2];
      } else {
         return Qg.q(var0, "Thumb-2/Miscellaneous System");
      }
   }
}
