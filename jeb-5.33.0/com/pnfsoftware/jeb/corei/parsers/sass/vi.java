package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionService;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorFamily;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.ckh;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class vi extends AbstractProcessor {
   private static final ILogger UT = GlobalLog.getLogger(vi.class);
   @SerStaticOk
   public static final ProcessorType pC;
   @SerStaticOk
   public static final p A = new p();
   @SerStaticOk
   public static final ICallingConvention kS;
   @SerStaticOk
   public static final Map wS = new HashMap();
   @SerId(1)
   private String E;
   @SerId(2)
   private int sY;
   @SerTransient
   private GK ys;

   static void pC() {
      Assert.a(pC != null && A != null);
   }

   static synchronized GK pC(String var0) {
      GK var1 = (GK)wS.get(var0);
      if (var1 == null) {
         try {
            try (InputStream var2 = SassPlugin.class.getResourceAsStream("isadefs/" + var0 + ".bin")) {
               ckh var3 = ckh.pC();
               SerializationManager var4 = new SerializationManager(var3);
               Deserializer var5 = var4.getDeserializer(var2);
               Mh var6 = (Mh)var5.deserialize();
               Assert.a(var6.pC.equals(var0));
               var1 = (GK)var5.deserialize();
            }

            wS.put(var0, var1);
         } catch (Exception var9) {
            throw new RuntimeException(var9);
         }
      }

      return var1;
   }

   @SerCustomInitPostGraph
   private void sY() {
      this.ys = pC(this.E);
   }

   public vi(String var1, int var2) {
      super(16, var2, Endianness.LITTLE_ENDIAN, 1);
      Assert.a(var2 == 32 || var2 == 64);
      int var3 = uX.pC(var1);
      Assert.a(var3 >= 70, "Unsupported Nvidia GPU target, must be Volta or above (sm_70+)");
      this.E = var1;
      this.sY = var3;
      this.sY();
   }

   public String A() {
      return this.E;
   }

   public int kS() {
      return this.sY;
   }

   public GK wS() {
      return this.ys;
   }

   public int UT() {
      if (this.sY < 75) {
         return 0;
      } else {
         return this.sY < 100 ? 64 : 256;
      }
   }

   public int E() {
      return this.sY < 75 ? 0 : 8;
   }

   @Override
   public ProcessorType getType() {
      return pC;
   }

   @Override
   public int getGPRegisterBitsize() {
      return 32;
   }

   @Override
   public String getRegisterName(long var1) {
      return this.pC(var1, false);
   }

   public String pC(long var1, boolean var3) {
      RegisterDescriptionEntry var4 = A.getDescriptionEntryById(var1);
      if (var4 != null) {
         if (var3) {
            List var5 = var4.getNames();
            if (var5.size() >= 2) {
               return (String)var5.get(1);
            }
         }

         return var4.getName();
      } else {
         return null;
      }
   }

   protected sy pC(byte[] var1, int var2, int var3) throws ProcessorException {
      Assert.a(var2 + 16 <= var3, "Not enough code");
      long var4 = EndianUtil.littleEndianBytesToLong(var1, var2);
      long var6 = EndianUtil.littleEndianBytesToLong(var1, var2 + 8);
      GK.KD var8 = this.ys.pC(var4, var6);
      GK.sy var9 = this.ys.pC(var8, var4, var6);
      sy var10 = new sy(Arrays.copyOfRange(var1, var2, var2 + 16), var8.pC, var8.kS);
      int var11 = var9.pC(var8.oT.A).intValue();
      int var12 = var9.pC(var8.oT.A + "@not").intValue();
      var10.wS = var12 != 0;
      var10.E = var11;
      if (var8.oT.pC.pC.equals("UniformPredicate")) {
         var10.UT = true;
      } else {
         Assert.a(var8.oT.pC.pC.equals("Predicate"));
      }

      List var13 = this.pC(var8.fI, var9, var10);
      var10.sY.addAll(var13);
      ArrayList var14 = new ArrayList();

      for (int var15 = 0; var15 < var8.WR.size(); var15++) {
         GK.yt var16 = (GK.yt)var8.WR.get(var15);
         GK.RC var17 = (GK.RC)var16.A.get(0);
         oP var18;
         if (var17.pC()) {
            String var32 = var17.pC.pC.pC.pC;
            if (var32.equals("C") || var32.equals("DESC")) {
               Assert.a(var16.A.size() == 3);
               GK.RC var39 = (GK.RC)var16.A.get(1);
               Assert.a(var39.A.size() == 1);
               Assert.a(var39.kS.isEmpty());
               oP var42 = this.pC(var39.pC(0), var9, var10);
               GK.RC var44 = (GK.RC)var16.A.get(2);
               Assert.a(var44.A.size() >= 1);
               ArrayList var45 = new ArrayList();
               oP var24 = null;

               for (GK.Sv var26 : var44.A) {
                  var24 = this.pC(var26, var9, var10);
                  var45.add(var24);
               }

               int var46 = (int)var24.getOperandValue();
               if (((GK.Sv)var39.A.get(0)).pC.A.endsWith("_bank") && var44.pC(-1).pC.A.endsWith("_addr")) {
                  int var48 = 1 << var8.xC;
                  var46 *= var48;
                  var24.pC(var46);
               }

               int var49 = var32.equals("C") ? 1 : 2;
               var18 = oP.pC(var49, var42, var45);
               List var27 = this.pC(var44.kS, var9, var10);
               var18.UT.addAll(var27);
            } else if (!var32.equals("TMA") && !var32.equals("GMMA")) {
               var18 = this.pC(var17.pC, var9, var10);
               if (var16.A.size() > 1) {
                  ArrayList var38 = new ArrayList();

                  for (GK.RC var43 : var16.A.subList(1, var16.A.size())) {
                     Assert.a(var43.pC());
                     var38.add(this.pC(var43.pC, var9, var10));
                  }

                  var18.E = var38;
               }
            } else {
               Assert.a(var16.A.size() == 2);
               GK.RC var37 = (GK.RC)var16.A.get(1);
               Assert.a(var37.A.size() == 1);
               oP var40 = this.pC(var37.pC(0), var9, var10);
               int var22 = var32.equals("TMA") ? 2 : 3;
               var18 = oP.pC(var22, var40);
               List var23 = this.pC(var37.kS, var9, var10);
               var18.UT.addAll(var23);
            }
         } else {
            Assert.a(var16.A.size() == 1);
            ArrayList var19 = new ArrayList();

            for (GK.Sv var21 : var17.A) {
               var19.add(this.pC(var21, var9, var10));
            }

            var18 = oP.pC(var19);
         }

         Assert.a(var18 != null);
         if (var16.pC != 0) {
            if ((var16.pC & 4) != 0) {
               long var33 = var9.pC(var17.pC.pC.A + "@absolute");
               if (var33 != 0L) {
                  Assert.a(var18.wS == 0);
                  var18.wS |= 4;
               }
            }

            if ((var16.pC & 8) != 0) {
               long var34 = var9.pC(var17.pC.pC.A + "@invert");
               if (var34 != 0L) {
                  Assert.a(var18.wS == 0);
                  var18.wS |= 8;
               }
            }

            if ((var16.pC & 2) != 0) {
               long var35 = var9.pC(var17.pC.pC.A + "@negate");
               if (var35 != 0L) {
                  Assert.a(var18.wS == 0 || var18.wS == 4);
                  var18.wS |= 2;
               }
            }

            if ((var16.pC & 1) != 0) {
               long var36 = var9.pC(var17.pC.pC.A + "@not");
               if (var36 != 0L) {
                  Assert.a(var18.wS == 0);
                  var18.wS |= 1;
               }
            }
         }

         var14.add(var18);
      }

      var10.kS = var8.kS;
      var10.ys = (oP[])var14.toArray(new oP[var14.size()]);

      for (GK.Av var29 : var8.NS) {
         oP var30 = this.pC(var29.A, var9, var10);
         oP var31 = var29.pC == null ? null : this.pC(var29.pC, var9, var10);
         var10.gp.add(new Couple(var31, var30));
      }

      return var10;
   }

   oP pC(GK.Sv var1, GK.sy var2, sy var3) {
      oP var4 = this.pC(var1.pC, var2, var3);
      List var5 = this.pC(var1.A, var2, var3);
      var4.UT.addAll(var5);
      return var4;
   }

   oP pC(GK.Ws var1, GK.sy var2, sy var3) {
      label125: {
         String var5 = var1.pC.pC;
         Long var6 = var2.pC(var1.A, var5, var3.pC(false));
         long var7;
         if (var6 != null) {
            var7 = var6;
         } else {
            Map var9 = this.ys.UT.pC(var5);
            Assert.a(var9 != null && new HashSet(var9.values()).size() == 1);
            var7 = ((Integer)((Entry)var9.entrySet().iterator().next()).getValue()).intValue();
         }

         byte var16 = 0;
         oP var4;

         var4 = switch (var5) {
            case "Register", "ZeroRegister", "NonZeroRegister" -> new oP(0, var16 == 0 ? 32 : var16, RegisterUtil.createPureRegisterId((int)var7, 0));
            case "UniformRegister", "ZeroUniformRegister", "NonZeroUniformRegister" -> new oP(
               0, var16 == 0 ? 32 : var16, RegisterUtil.createPureRegisterId((int)var7, 4)
            );
            case "Predicate" -> new oP(0, 1, RegisterUtil.createPureRegisterId((int)var7, 2));
            case "UniformPredicate" -> new oP(0, 1, RegisterUtil.createPureRegisterId((int)var7, 5));
            case "SpecialRegister" -> new oP(0, 32, RegisterUtil.createPureRegisterId((int)var7, 1));
            case "UImm" -> {
               int var19 = var1.pC.A;
               yield new oP(1, var19, var7);
            }
            case "SImm" -> {
               int var18 = var1.pC.A;
               yield new oP(9, var18, var7);
            }
            case "RSImm" -> {
               int var17 = var1.pC.A;
               yield new oP(4097, var17, var7);
            }
            case "F16Imm" -> new oP(4100, 16, var7);
            case "F32Imm" -> new oP(4100, 32, var7);
            case "F64Imm" -> new oP(4100, 64, var7);
            case "BITSET" -> new oP(4101, 64, var7);
            default -> {
               int var12 = (int)var7;
               LinkedHashSet var13 = this.ys.UT.pC(var5, var12);
               if (var13.isEmpty()) {
                  var13.add(var5 + "_" + var12);
                  boolean var14 = false;
                  if (var5.equals("BATCH_T") && var12 == 3) {
                     var14 = true;
                  }

                  if (!var14) {
                     RuntimeException var15 = new RuntimeException(
                        Strings.ff("sass: no entry found for value %d for type '%s' (opcode class: %s)", var12, var5, var2.A.pC)
                     );
                     if (Licensing.isDebugBuild()) {
                        throw var15;
                     }

                     JebCoreService.notifySilentExceptionToClient(var15);
                  }
               }

               String var20 = (String)CollectionUtil.last(var13);
               yield oP.pC(var5, var20, var7);
               if (!var1.pC.E) {
                  var4.pC = this.pC(var1.pC, var7);
               }

               return var4;
            }
         };
      }
   }

   boolean pC(GK.HE var1, long var2) {
      return var1.kS != null && var1.kS == var2 ? true : var1.wS != null && this.ys.UT.pC(var1.pC, (int)var2, var1.wS);
   }

   List pC(List var1, GK.sy var2, sy var3) {
      ArrayList var4 = new ArrayList();

      for (GK.Ws var6 : var1) {
         String var7 = null;
         Object var8 = null;
         String var9 = var6.pC.pC;
         if (var9.equals("PSEUDO_OPCODE")) {
            var9 = "PSEUDO_OPCODE1";
         }

         Long var10 = var2.pC(var6.A);
         if (var10 == null) {
            Map var11 = this.ys.UT.pC(var9);
            if (var11 != null) {
               Assert.a(new HashSet(var11.values()).size() == 1);
               var8 = var11.keySet();
               if (var7 == null) {
                  var7 = (String)CollectionUtil.last((Collection)var8);
               }
            }
         }

         if (var8 == null) {
            var8 = this.ys.UT.pC(var9, var10.intValue());
            Assert.a(var8 != null && !var8.isEmpty());
            var7 = (String)CollectionUtil.last((Collection)var8);
         }

         Assert.a(var7 != null);
         boolean var12 = var6.pC.wS != null && var8.contains(var6.pC.wS);
         if (var9.equals("EXP_DESC")) {
            if (var7.equalsIgnoreCase("noexp_desc")) {
               var3.ld = false;
            } else if (var7.equalsIgnoreCase("EXP")) {
               var3.ld = true;
            }
         } else {
            var4.add(new qt(var9, var7, var12));
         }
      }

      return var4;
   }

   static {
      ProcessorFamily var0 = ProcessorFamily.register(190, "cuda");
      pC = ProcessorType.register(100, "sass_visa", var0);
      Assert.a(RegisterBankService.getInstance().add(pC, A) == null);
      kS = new CallingConventionBuilder("__sass", pC).build();
      Assert.a(CallingConventionService.getInstance().addConvention(kS));
   }
}
