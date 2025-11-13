package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cfr {
   private static final ILogger pC = GlobalLog.getLogger(cfr.class);
   private static final cfs[] A = new cfs[]{
      cfs.pC("ADDI", true, cfs.A),
      cfs.pC("ADDIU", true),
      cfs.pC("SLTI", true),
      cfs.pC("SLTIU", true),
      cfs.pC("ANDI", false),
      cfs.pC("ORI", false),
      cfs.pC("XORI", false),
      cfs.pC("AUI", true, cfs.kS)
   };
   private static final cfs kS = cfs.pC("DAUI", true, cfs.NS);
   private static final cfs wS = new cfs("LUI", cfn.kS, cfl.Ab);
   private static final cfs UT = new cfs("LI", cfn.kS, cfl.ld);
   private static final cfs[] E = new cfs[]{cfs.A("DADDI", true, cfs.oT), cfs.A("DADDIU", true, cfs.gp), cfs.E("LDL", cfs.oT), cfs.E("LDR", cfs.oT)};
   private static final cfs[] sY = new cfs[]{
      cfs.wS("LB"), cfs.wS("LH"), cfs.wS("LWL", cfs.A), cfs.wS("LW"), cfs.wS("LBU"), cfs.wS("LHU"), cfs.wS("LWR", cfs.A), cfs.E("LWU", cfs.gp)
   };
   private static final cfs[] ys = new cfs[]{
      cfs.wS("SB"),
      cfs.wS("SH"),
      cfs.wS("SWL", cfs.A),
      cfs.wS("SW"),
      cfs.E("SDL", cfs.oT),
      cfs.E("SDR", cfs.oT),
      cfs.wS("SWR", cfs.A),
      new cfs("CACHE", cfs.A, cfl.Sc, cfs.vP)
   };
   private static final cfs[] ld = new cfs[]{
      cfs.wS("LL", cfs.ys),
      cfs.UT("LWC1"),
      cfs.wS("LWC2", cfs.A),
      new cfs("PREF", cfs.ld, cfl.Sc, cfs.vP),
      cfs.E("LLD", cfs.oT),
      cfs.UT("LDC1"),
      cfs.wS("LDC2", cfs.ys),
      cfs.E("LD", cfs.gp)
   };
   private static final cfs gp = cfs.wS("LWC3");
   private static final cfs[] oT = new cfs[]{
      cfs.wS("SC", cfs.ys),
      cfs.UT("SWC1"),
      cfs.wS("SWC2", cfs.A),
      cfs.pC,
      cfs.E("SCD", cfs.gp),
      cfs.UT("SDC1", cfs.sY),
      cfs.wS("SDC2", cfs.ys),
      cfs.E("SD", cfs.gp)
   };
   private static final cfk[] fI = new cfk[]{cfn.pC, cfl.wS};
   private static final cfs[] WR = new cfs[]{new cfs("LAPC", fI), new cfs("LWPC", cfs.kS, fI), new cfs("LWUPC", cfs.NS, fI)};
   private static final cfs NS = new cfs("LDPC", cfs.NS, cfn.pC, cfl.kS);
   private static final cfs vP = new cfs("AUIPC", cfs.kS, cfn.pC, cfl.ld);
   private static final cfs xC = new cfs("ALUIPC", cfs.kS, cfn.pC, cfl.ld);

   public static cfs pC(byte[] var0, com.pnfsoftware.jeb.corei.parsers.mips.Tb var1, boolean var2, int var3) throws ProcessorException {
      int var4 = (var0[0] & 224) >>> 5;
      int var5 = (var0[0] & 28) >>> 2;
      int var6 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var7 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var4) {
         case 0:
            switch (var5) {
               case 0:
                  return cgb.pC(var0, var1, var2);
               case 1:
                  return cgb.A(var0, var1, var2);
               case 2:
                  return cgc.ys;
               case 3:
                  return cgc.ld;
               case 4:
                  if (var6 == 0) {
                     return var7 == 0 ? cgc.oT : cgc.ah;
                  }

                  return cgc.NS;
               case 5:
                  if (var6 == 0) {
                     return cgc.eP;
                  }

                  return cgc.vP;
               case 6:
                  if (var6 == 0) {
                     return cgc.UO;
                  }

                  return cgc.pC(var7, var6);
               case 7:
                  if (var6 == 0) {
                     return cgc.Ab;
                  }

                  return cgc.A(var7, var6);
               default:
                  return cfs.pC(var0, "000");
            }
         case 1:
            if (var1.A() && var5 == 0) {
               cfs var9 = cgc.kS(var7, var6);
               if (var9 != null) {
                  return var9;
               }
            } else {
               if (var5 == 7 && var7 == 0) {
                  return wS;
               }

               if (var5 == 1 && var7 == 0) {
                  return UT;
               }
            }

            return A[var5];
         case 2:
            switch (var5) {
               case 0:
                  return cgb.pC(var0);
               case 1:
                  return cgb.kS(var0, var1, var2);
               case 2:
                  return cgb.A(var0);
               case 3:
                  return cgb.kS(var0);
               case 4:
                  return cgc.xC;
               case 5:
                  return cgc.ED;
               case 6:
                  if (var6 == 0) {
                     return cgc.rl;
                  }

                  return cgc.wS(var7, var6);
               case 7:
                  if (var6 == 0) {
                     return cgc.z;
                  }

                  return cgc.UT(var7, var6);
               default:
                  return cfs.pC(var0, "010");
            }
         case 3:
            switch (var5) {
               case 0:
                  if (var1.A()) {
                     return cgc.E(var7, var6);
                  } else if (var1.kS()) {
                     return E[var5];
                  }
               default:
                  return cfs.pC(var0, "011_" + var5);
               case 1:
               case 2:
               case 3:
                  return E[var5];
               case 4:
                  return cgb.wS(var0, var1, var2);
               case 5:
                  if (var1.A()) {
                     if (var7 == 0) {
                        return cfs.A(var0, "3" + var5);
                     }

                     return kS;
                  }

                  return cgc.gp;
               case 6:
                  return cgb.wS(var0);
               case 7:
                  return cgb.pC(var0, var1, var2, var3);
            }
         case 4:
            return sY[var5];
         case 5:
            return ys[var5];
         case 6:
            if (var1.A()) {
               if (var5 == 2) {
                  return cgc.fI;
               }

               if (var5 == 6) {
                  return cgc.pC(var0, var7);
               }
            }

            if (var5 == 3 && var1.pC() <= com.pnfsoftware.jeb.corei.parsers.mips.Tb.kS.pC()) {
               return gp;
            }

            return ld[var5];
         case 7:
            if (var1.A()) {
               if (var5 == 2) {
                  return cgc.WR;
               }

               if (var5 == 6) {
                  return cgc.A(var0, var7);
               }
            }

            if (var5 == 3) {
               int var8 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
               if (var8 < 24) {
                  return WR[var8 >>> 3];
               }

               if (var8 < 28) {
                  return NS;
               }

               if (var8 == 30) {
                  return vP;
               }

               if (var8 == 31) {
                  return xC;
               }
            }

            return oT[var5];
         default:
            return null;
      }
   }
}
