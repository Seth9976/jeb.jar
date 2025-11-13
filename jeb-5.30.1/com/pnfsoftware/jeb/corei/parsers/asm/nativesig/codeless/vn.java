package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessLibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessLibraryVersion;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ExecutableModelMetadata;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Module;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ModuleId;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Bu;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.SetMap;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.axu;
import com.pnfsoftware.jebglobal.axv;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class vn {
   private static final ILogger za = GlobalLog.getLogger(vn.class);
   private static final boolean lm = false;
   private static final int zz = 8;
   private Map JY = new HashMap();
   private Map HF = new HashMap();
   private SetMap LK = new SetMap();
   static vn.CU q = new vn.CU();
   static vn.CU RF = new vn.CU();
   static vn.CU xK = new vn.CU();
   static vn.CU Dw = new vn.CU();
   static vn.CU Uv = new vn.CU();
   static vn.CU oW = new vn.CU();
   static vn.CU gO = new vn.CU();
   static vn.CU nf = new vn.CU();
   static Map gP = new HashMap();

   public ej q(String var1, boolean var2) throws JebException, IOException {
      return this.q(var1, var2, null, null);
   }

   public ej q(String var1, boolean var2, CodelessLibraryID var3, CodelessLibraryVersion var4) throws JebException, IOException {
      List var5 = this.RF(var1);
      if (var5.isEmpty()) {
         Object[] var11 = new Object[]{var1};
         return null;
      } else {
         Object[] var10000 = new Object[]{var5};
         ej var6 = new ej();
         ExecutableModelMetadata var7 = new ExecutableModelMetadata(var3, var4, 0);
         var6.q(var7);

         for (File var9 : var5) {
            this.q(var9, var6, var2);
            var10000 = new Object[]{var9.getName()};
         }

         this.q(var6);
         return var6;
      }
   }

   private void q(ej var1) {
      this.Uv(var1);
      this.oW(var1);

      for (Func var3 : var1.Dw().keySet()) {
         for (Func var5 : var1.Dw().get(var3)) {
            var1.q(var5.getModuleId(), var5);
         }
      }

      this.xK(var1);
      this.Dw(var1);
      this.RF(var1);
   }

   private void RF(ej var1) {
      for (tw var3 : var1.RF().keySet()) {
         for (Func var5 : var1.RF().get(var3)) {
            var1.xK().put(var5, var3);
         }
      }
   }

   private void xK(ej var1) {
      for (Func var3 : var1.Dw().keySet()) {
         for (Func var5 : var1.Dw().get(var3)) {
            var1.Uv().put(var5, var3);
         }
      }
   }

   private void Dw(ej var1) {
      for (Func var3 : var1.oW().keySet()) {
         for (Func var5 : var1.oW().get(var3)) {
            var1.gO().put(var5, var3);
         }
      }
   }

   private void Uv(ej var1) {
      SetMap var2 = new SetMap();

      for (Func var4 : var1.Dw().keySet()) {
         for (Func var6 : var1.Dw().get(var4)) {
            if (var6.getModuleId().isUnknown()) {
               Func var7 = this.q(var1, var6);
               if (var7 != null) {
                  var2.put(var4, var7);
               } else {
                  var2.put(var4, var6);
               }
            } else {
               var2.put(var4, var6);
            }
         }
      }

      var1.q(var2);
   }

   private void oW(ej var1) {
      SetMap var2 = new SetMap();

      for (Func var4 : var1.oW().keySet()) {
         for (Func var6 : var1.oW().get(var4)) {
            if (var6.getModuleId().isUnknown()) {
               Func var7 = this.q(var1, var6);
               if (var7 != null) {
                  var2.put(var4, var7);
               } else {
                  var2.put(var4, var6);
               }
            } else {
               var2.put(var4, var6);
            }
         }
      }

      var1.xK(var2);
   }

   private Func q(ej var1, Func var2) {
      Assert.a(var2.getModuleId().isUnknown());
      Set var3 = this.LK.get(var2.getName());
      if (var3 != null && !var3.isEmpty()) {
         Set var11 = (Set)var3.stream().filter(var0 -> !var0.getModuleId().isUnknown()).collect(Collectors.toSet());
         if (var11.size() == 0) {
            Object[] var16 = new Object[]{var2};
            return null;
         } else {
            if (var11.size() > 1) {
               Object[] var15 = new Object[]{var2, var3};
            }

            return (Func)var11.iterator().next();
         }
      } else {
         Module var4 = null;

         for (Entry var6 : this.JY.entrySet()) {
            ICodeObjectUnit var7 = (ICodeObjectUnit)((INativeCodeUnit)var6.getKey()).getParent();

            for (ISymbolInformation var10 : CodeObjectUnitUtil.findAllSymbolsByName(var7, var2.getName())) {
               if (!var10.getType().isExtern()) {
                  if (var4 != null && !var4.equals(var6.getValue())) {
                     Object[] var10000 = new Object[]{var2.getName(), var4, var6.getValue()};
                  }

                  var4 = (Module)var6.getValue();
               }
            }
         }

         if (var4 != null) {
            Object[] var14 = new Object[]{var2.getName(), var4};
            Func var12 = Func.createFrom(var2.getName(), var4.getId());
            this.q(var12);
            var1.q(var4.getId(), var12);
            return var12;
         } else {
            Object[] var13 = new Object[]{var2};
            return null;
         }
      }
   }

   private List RF(String var1) {
      ArrayList var2 = new ArrayList();
      var2.addAll(IO.listFiles(var1));
      return var2;
   }

   protected Func q(String var1, Module var2) {
      Set var3 = this.LK.get(var1);
      if (var3 != null && !var3.isEmpty()) {
         for (Func var5 : var3) {
            if (var5.getModuleId().equals(var2.getId())) {
               return var5;
            }
         }

         Object[] var6 = new Object[]{var1, var2};
         return null;
      } else {
         Object[] var10000 = new Object[]{var1, var2};
         return null;
      }
   }

   protected Set q(String var1) {
      return this.LK.get(var1);
   }

   private void q(Func var1) {
      this.LK.put(var1.getName(), var1);
   }

   protected String q(INativeMethodItem var1) {
      String var2 = ((axp)var1).mI();
      return var2 != null ? var2 : var1.getName();
   }

   private boolean q(File var1, ej var2, boolean var3) throws JebException, IOException {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI("x86", 1, var3 ? 2 : 0, null, false);
      var4.q(true);
      HeadlessClientContext var5 = Bu.q(var4);

      try {
         List var6 = Bu.q(var5, var1.getParent(), var1.getName());
         if (var6.size() != 1) {
            za.error("Problem with root unit creation");
            return false;
         } else if (((IUnit)var6.get(0)).getChildren().size() == 0) {
            za.error("No children units were created");
            return false;
         } else {
            ArrayList var7 = new ArrayList();
            String var8 = ((IUnit)var6.get(0)).getFormatType();
            if (var8.equals("ar")) {
               var7.addAll((Collection)((IUnit)var6.get(0)).getChildren().stream().filter(var0 -> var0 instanceof ICodeObjectUnit).collect(Collectors.toSet()));
            } else {
               if (!var8.equals("wincoff") && !var8.equals("elf") && !var8.equals("winpe")) {
                  za.error("> non-suitable file format for model generation");
                  return false;
               }

               var7.add((ICodeObjectUnit)var6.get(0));
            }

            int var9 = 0;
            ArrayList var10 = new ArrayList();

            for (ICodeObjectUnit var12 : var7) {
               Object[] var10000 = new Object[]{var12.getName(), ++var9, var7.size()};
               if (var12.getChildren().size() != 0 || var12.process() && var12.getChildren().size() != 0) {
                  List var14 = UnitUtil.findChildrenByType(var12, INativeCodeUnit.class, false);
                  if (var14 == null || var14.size() != 1) {
                     za.error("Cannot find unique PBCU");
                     return false;
                  }

                  INativeCodeUnit var13 = (INativeCodeUnit)var14.get(0);
                  if (!var13.isProcessed()) {
                     try {
                        if (!var13.process()) {
                           za.error("Problem while processing code unit!");
                           return false;
                        }
                     } catch (Exception var20) {
                        za.error("Problem while processing code unit!");
                        return false;
                     }
                  }

                  var10.add(var13);
               }
            }

            this.q(var10, var2, var3, true);
            return true;
         }
      } finally {
         Bu.q(var5, var4);
      }
   }

   public void q(INativeCodeUnit var1, ej var2, boolean var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add(var1);
      this.q(var5, var2, var3, var4);
      this.q(var2);
   }

   private void q(List var1, ej var2, boolean var3, boolean var4) {
      for (INativeCodeUnit var6 : var1) {
         this.q(var6, var2, var3);
      }

      for (String var16 : this.LK.keySet()) {
         if (this.LK.get(var16).size() > 1) {
            Object[] var10000 = new Object[]{var16, this.LK.get(var16)};
         }
      }

      for (INativeCodeUnit var17 : var1) {
         this.RF(var17, var2, var3, var4);
      }

      if (var3) {
         HashMap var15 = new HashMap();

         for (INativeCodeUnit var7 : var1) {
            for (INativeMethodItem var9 : var7.getInternalMethods()) {
               long var10 = this.RF(var9);
               Object var12 = (List)var15.get(var10);
               if (var12 == null) {
                  var12 = new ArrayList();
                  var15.put(var10, var12);
               }

               var12.add((Func)this.HF.get(var9));
            }
         }

         SetMap var19 = var2.gP();

         for (Entry var21 : var15.entrySet()) {
            Set var22 = var19.get((Long)var21.getKey());
            if (var22 != null) {
               var22.addAll((Collection)var21.getValue());
            } else if (((List)var21.getValue()).size() >= 2) {
               var19.putMulti((Long)var21.getKey(), (Collection)var21.getValue());
            }
         }
      }
   }

   private long RF(INativeMethodItem var1) {
      long var2 = 0L;

      for (IInstruction var6 : var1.getData().getCFG().getInstructions()) {
         var2 += var6.toString().hashCode();
      }

      return var2;
   }

   private boolean q(INativeCodeUnit var1, ej var2, boolean var3) {
      Module var4 = Module.createKnownModule(new ModuleId(new File(var1.getParent().getName()).getName()));
      this.JY.put(var1, var4);

      for (INativeMethodItem var6 : var1.getInternalMethods()) {
         String var7 = this.q(var6);
         Func var8 = Func.createFrom(var7, var4.getId());
         if (!var3) {
            var8.setAddresses(var6.getMemoryAddress(), var6.getData().getCFG().getFirstAddress(), var6.getData().getCFG().getEndAddress() - 1L);
         }

         this.q(var8);
         this.HF.put((axp)var6, var8);
         var2.q(var4.getId(), var8);
      }

      if (var1.getDetectedCompiler().isLinuxCompatible() && var1.getCodeObjectContainer() instanceof IELFUnit) {
         for (INativeMethodItem var12 : var1.getInternalMethods()) {
            Func var13 = (Func)this.HF.get(var12);
            if (var13 != null) {
               ISegmentInformation var14 = CodeObjectUnitUtil.findSectionByRelativeAddress(
                  var1.getCodeObjectContainer(), var12.getMemoryAddress() - var1.getVirtualImageBase()
               );
               if (var14 != null && var14.getName().equals(".plt")) {
                  INativeMethodItem var9 = var12.getData().getTrampolineTarget();
                  if (var9 != null && var9.getData() != null) {
                     Func var10 = (Func)this.HF.get(var9);
                     if (var10 != null) {
                        var13.setTrampolineTarget(var10);
                     } else {
                        Object[] var10000 = new Object[]{var9};
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   private boolean RF(INativeCodeUnit var1, ej var2, boolean var3, boolean var4) {
      vn.eo var5 = new vn.eo((abg)var1, var2, this, 2, 1);
      boolean var6 = this.q(var1);
      if (!var5.q(var3, var6, var3, true, true)) {
         za.error("> problem while collecting string references");
      }

      if (!var5.q(var3, var6)) {
         za.error("> problem while collecting constant references");
      }

      if (var4) {
         if (!var5.RF()) {
            za.error("> problem while extracting call graph");
         }

         if (!var5.q()) {
            za.error("> problem while extracting reference graph");
         }
      }

      return true;
   }

   private boolean q(INativeCodeUnit var1) {
      ICodeObjectUnit var2 = (ICodeObjectUnit)var1.getParent();
      if (var2 != null && var2.getFormatType().equals("wincoff")) {
         for (ISymbolInformation var4 : var2.getSymbols()) {
            if (var4.getType() == SymbolType.UNKNOWN && var4.getName().startsWith("??_C@")) {
               return true;
            }
         }
      }

      return false;
   }

   private static void q(String var0, Object... var1) {
   }

   static {
      RF.q.add(Pattern.compile(".*openssl.*", 2));
      RF.q.add(Pattern.compile(".*ssl.*", 2));
      RF.q.add(Pattern.compile(".*crypto.*", 2));
      RF.q.add(Pattern.compile(".*\\.c$", 2));
      RF.q.add(Pattern.compile(".*ctx->.*"));
      xK.q.add(Pattern.compile(".*curl.*", 2));
      xK.q.add(Pattern.compile(".*\\.c$", 2));
      Dw.q.add(Pattern.compile(".*json.*", 2));
      Dw.q.add(Pattern.compile("double out of Int64 range"));
      Dw.q.add(Pattern.compile("double out of UInt64 range"));
      Dw.q.add(Pattern.compile("LargestInt out of UInt range"));
      Dw.q.add(Pattern.compile("LargestInt out of UInt64 range"));
      Dw.q.add(Pattern.compile("LargestUInt out of Int64 range"));
      Dw.q.add(Pattern.compile("LargestUInt out of UInt range"));
      Dw.q.add(Pattern.compile("Value is not convertible to Int64"));
      Dw.q.add(Pattern.compile("Value is not convertible to UInt"));
      Dw.q.add(Pattern.compile("Value is not convertible to UInt64"));
      Dw.q.add(Pattern.compile("double out of Int range"));
      Dw.q.add(Pattern.compile("double out of UInt range"));
      Dw.q.add(Pattern.compile("LargestInt out of Int range"));
      Dw.q.add(Pattern.compile("LargestUInt out of Int range"));
      Dw.q.add(Pattern.compile("Type is not convertible to string"));
      Dw.q.add(Pattern.compile("Value is not convertible to Int"));
      Dw.q.add(Pattern.compile("A valid JSON document must be either an array or an object value."));
      Dw.q.add(Pattern.compile("Exceeded stackLimit in readValue()."));
      Dw.q.add(Pattern.compile("Syntax error: value, object or array expected."));
      Dw.q.add(Pattern.compile("Missing ':' after object member name"));
      Dw.q.add(Pattern.compile("Missing ',' or '}' in object declaration"));
      Dw.q.add(Pattern.compile("Missing '}' or object member name"));
      Dw.q.add(Pattern.compile("Missing ',' or ']' in array declaration"));
      Dw.q.add(Pattern.compile("additional six characters expected to parse unicode surrogate pair."));
      Dw.q.add(Pattern.compile("Bad unicode escape sequence in string: four digits expected."));
      Dw.q.add(Pattern.compile("Bad unicode escape sequence in string: hexadecimal digit expected."));
      Uv.xK = 8;
      oW.q.add(Pattern.compile(".*key .*", 2));
      oW.q.add(Pattern.compile(".*ssh.*", 2));
      gO.xK = 14;
      nf.q.add(Pattern.compile(".*poco.*", 2));
      gP.put(CodelessLibraryID.OpenSSL, RF);
      gP.put(CodelessLibraryID.libCURL, xK);
      gP.put(CodelessLibraryID.bzip2, Uv);
      gP.put(CodelessLibraryID.libSSH2, oW);
      gP.put(CodelessLibraryID.zlib, gO);
      gP.put(CodelessLibraryID.jsoncpp, Dw);
      gP.put(CodelessLibraryID.poco, nf);
   }

   private static class CU {
      List q = new ArrayList();
      List RF = new ArrayList();
      int xK = 0;
   }

   public static class eo {
      abg q;
      ej RF;
      vn xK;
      vn.CU Dw;
      Module Uv;
      int oW;
      int gO;
      Pattern nf = Pattern.compile("^([a-zA-Z]:\\\\).*");
      Pattern gP = Pattern.compile("^(\\.\\\\).*");
      Pattern za = Pattern.compile("^(/).*");
      Pattern lm = Pattern.compile("^(\\./).*");
      Pattern zz = Pattern.compile(".*\\.c$");

      eo(abg var1, ej var2, vn var3, int var4, int var5) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Uv = (Module)var3.JY.get(var1);
         this.oW = var4;
         this.gO = var5;
         this.Dw = var2.JY() != null && vn.gP.containsKey(var2.JY().getLibraryId()) ? (vn.CU)vn.gP.get(var2.JY().getLibraryId()) : vn.q;
      }

      public boolean q(boolean var1, boolean var2) {
         SortedMap var3 = this.q
            .getCodeModel()
            .getItemsInRange(this.q.getVirtualImageBase(), true, this.q.getVirtualImageBase() + this.q.getImageSize(), true, var0 -> var0 instanceof axv);
         Iterator var4 = var3.entrySet().iterator();

         while (true) {
            long var7;
            byte[] var13;
            while (true) {
               if (!var4.hasNext()) {
                  return true;
               }

               Entry var5 = (Entry)var4.next();
               INativeContinuousItem var6 = (INativeContinuousItem)var5.getValue();
               var7 = var6.getMemoryAddress();
               int var9 = 8;
               INativeContinuousItem var10 = this.q.getCodeModel().getNextItem(var7);
               if (var10 != null) {
                  long var11 = var10.getMemoryAddress();
                  var9 = (int)Math.min((long)var9, var11 - var7);
               }

               if (var9 >= this.gO) {
                  var13 = new byte[var9];

                  try {
                     this.q.getMemory().read(var7, var9, var13, 0);
                     break;
                  } catch (MemoryException var25) {
                  }
               }
            }

            long var26 = EndianUtil.bytesToNumberUnsigned(this.q.getProcessor().getEndianness().toByteOrder(), var13);
            if (var26 != 0L) {
               long var14 = this.q.getVirtualImageBase();
               long var16 = this.q.zz();
               if (var26 < var14 || var26 > var16) {
                  Set var18 = this.q.getCodeModel().getReferenceManager().getReferencesTo(var7);
                  if (!var18.isEmpty()) {
                     nI var19 = new nI(var26, iZ.q);
                     HashSet var20 = new HashSet();

                     for (IReference var22 : var18) {
                        axp var23 = this.q.q(var22.getFrom().getInternalAddress(), false);
                        Func var24 = (Func)this.xK.HF.get(var23);
                        if (var24 != null) {
                           var20.add(var24);
                        }
                     }

                     if (!var20.isEmpty()) {
                        this.RF.RF().putMulti(var19, var20);
                     }
                  }
               }
            }
         }
      }

      private boolean q(long var1) {
         long var3 = this.q.getVirtualImageBase();
         List var5 = CodeObjectUnitUtil.findAllSymbolsByRelativeAddress((ICodeObjectUnit)this.q.getParent(), var1 - var3);
         return var5.isEmpty() ? false : var5.stream().filter(var0 -> var0.getName().startsWith("??_C@")).count() == 0L;
      }

      public boolean q(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5) {
         SortedMap var6 = this.q
            .getCodeModel()
            .getItemsInRange(
               this.q.getVirtualImageBase(), true, this.q.getVirtualImageBase() + this.q.getImageSize(), true, var0 -> var0 instanceof INativeDataItem
            );
         long var7 = this.q.getVirtualImageBase();
         Iterator var9 = var6.entrySet().iterator();

         while (true) {
            long var12;
            String var14;
            iZ var16;
            label122:
            while (true) {
               if (!var9.hasNext()) {
                  return true;
               }

               Entry var10 = (Entry)var9.next();
               INativeContinuousItem var11 = (INativeContinuousItem)var10.getValue();
               var12 = 0L;
               var14 = null;
               if (var11 instanceof INativeStringItem) {
                  var12 = var11.getMemoryAddress();
                  var14 = ((INativeStringItem)var11).getValue();
               }

               if (var12 != 0L && var2 && CodeObjectUnitUtil.findAllSymbolsByRelativeAddress((ICodeObjectUnit)this.q.getParent(), var12 - var7).isEmpty()) {
                  var12 = 0L;
               }

               if (var12 != 0L
                  && var14 != null
                  && var4
                  && (
                     this.nf.matcher(var14).matches()
                        || this.gP.matcher(var14).matches()
                        || this.za.matcher(var14).matches()
                        || this.lm.matcher(var14).matches()
                        || this.zz.matcher(var14).matches()
                  )) {
                  File var15 = new File(var14);
                  if (!var15.getName().isEmpty()) {
                     var14 = var15.getName();
                  }
               }

               if ((var14 == null || var14.length() >= this.oW) && var12 != 0L && var14 != null) {
                  String var25 = var14.trim();
                  var16 = iZ.q;
                  if (!var1) {
                     break;
                  }

                  boolean var17 = false;

                  for (Pattern var19 : this.Dw.RF) {
                     if (var19.matcher(var25).matches()) {
                        var17 = true;
                        break;
                     }
                  }

                  if (!var17) {
                     Iterator var28 = this.Dw.q.iterator();

                     Pattern var30;
                     do {
                        if (!var28.hasNext()) {
                           break label122;
                        }

                        var30 = (Pattern)var28.next();
                     } while (!var30.matcher(var25).matches());

                     var16 = iZ.xK;
                     break;
                  }
               }
            }

            PY var27 = new PY(var14, var16);
            Set var29 = this.q.getCodeModel().getReferenceManager().getReferencesTo(var12);
            boolean var31 = false;
            if (!var29.isEmpty()) {
               if (this.Dw.xK != 0 && var14.length() >= this.Dw.xK) {
                  var16 = iZ.xK;
                  var27 = new PY(var14, var16);
               }

               HashSet var20 = new HashSet();

               for (IReference var22 : var29) {
                  axp var23 = this.q.q(var22.getFrom().getInternalAddress(), false);
                  Func var24 = (Func)this.xK.HF.get(var23);
                  if (var24 != null) {
                     var20.add(var24);
                  }
               }

               if (!var20.isEmpty()) {
                  this.RF.RF().putMulti(var27, var20);
               }
            } else if (!var31 && var5) {
               if (var1 && var16 == iZ.xK) {
                  this.RF.za().add(var27);
               } else if (!var1) {
                  this.RF.za().add(var27);
               }
            }
         }
      }

      public boolean q() {
         IReferenceManager var1 = this.q.getCodeModel().getReferenceManager();
         SetMap var2 = this.RF.oW();

         for (axp var4 : this.q.getInternalMethods()) {
            Func var5 = (Func)this.xK.HF.get(var4);
            if (var5 == null) {
               throw new JebRuntimeException("cannot find ref func");
            }

            CFG var6 = var4.oW().getCFG();

            for (long var8 : var6.getInstructionsMap().keySet()) {
               for (IReference var11 : (Set)var1.getReferencesFrom(var8)
                  .stream()
                  .filter(var0 -> var0.getTo().isInternalAddress() && var0.getType() == ReferenceType.GEN_DATA)
                  .collect(Collectors.toSet())) {
                  Func var12 = null;
                  if (this.q.RF(var11.getTo().getInternalAddress()) != null) {
                     axp var13 = this.q.RF(var11.getTo().getInternalAddress());
                     var12 = (Func)this.xK.HF.get(var13);
                     if (var12 == null) {
                        throw new JebRuntimeException("cannot find func");
                     }
                  } else if (this.q.getNativeItemAt(var11.getTo().getInternalAddress()) instanceof axu) {
                     axp var14 = (axp)((axu)this.q.getNativeItemAt(var11.getTo().getInternalAddress())).cC();
                     var12 = this.xK.q(this.xK.q(var14), this.Uv);
                     if (var12 == null) {
                        Object[] var10000 = new Object[]{this.xK.q(var14)};
                        var12 = Func.createUnknownFrom(this.xK.q(var14));
                        this.xK.q(var12);
                     }
                  }

                  if (var12 != null) {
                     var2.put(var5, var12);
                  }
               }
            }
         }

         return true;
      }

      public boolean RF() {
         ICallGraph var1 = this.q.getCodeModel().getCallGraphManager().getGlobalCallGraph();
         long var2 = this.q.getVirtualImageBase();

         for (axp var5 : this.q.getInternalMethods()) {
            Func var6 = (Func)this.xK.HF.get(var5);
            if (var6 == null) {
               throw new JebRuntimeException("cannot find caller Func");
            }

            Object var7 = this.RF.Dw().get(var6);
            if (var7 == null) {
               var7 = new HashSet();
            }

            for (CallGraphVertex var9 : var1.getCallees(var5, false)) {
               if (var9.isInternal() || var9.getDereferencedAddress() != -1L) {
                  long var18 = var9.isInternal() ? var9.getInternalAddress().getAddress() : var9.getDereferencedAddress();
                  axp var13 = this.q.RF(var18);
                  Func var17;
                  if (var13 == null) {
                     List var14 = CodeObjectUnitUtil.findAllSymbolsByRelativeAddress((ICodeObjectUnit)this.q.getParent(), var18 - var2);
                     if (var14.size() < 1) {
                        Object[] var23 = new Object[]{this.xK.q(var5), var18};
                        continue;
                     }

                     if (var14.size() > 1) {
                        Object[] var20 = new Object[]{var18, var14};
                     }

                     String var15 = ((ISymbolInformation)var14.get(0)).getName();
                     Set var16 = this.xK.q(var15);
                     if (var16 != null && !var16.isEmpty()) {
                        if (var16.size() > 1) {
                           Object[] var22 = new Object[]{var18, this.xK.q(var5)};
                        }

                        var17 = (Func)var16.iterator().next();
                     } else {
                        Object[] var21 = new Object[]{var15};
                        var17 = Func.createUnknownFrom(var15);
                        this.xK.q(var17);
                     }
                  } else {
                     var17 = (Func)this.xK.HF.get(var13);
                  }

                  if (var17 != null) {
                     var7.add(var17);
                  }
               } else if (var9.getRoutine() == null) {
                  Object[] var19 = new Object[]{var9};
               } else {
                  Set var11 = this.xK.q(this.xK.q(var9.getRoutine()));
                  if (var11 != null && !var11.isEmpty()) {
                     if (var11.size() > 1) {
                        Object[] var10000 = new Object[]{var9.getRoutine()};
                     }

                     Func var10 = (Func)var11.iterator().next();
                     var7.add(var10);
                  } else {
                     Func var12 = Func.createUnknownFrom(this.xK.q(var9.getRoutine()));
                     this.xK.q(var12);
                     var7.add(var12);
                  }
               }
            }

            if (!var7.isEmpty()) {
               this.RF.Dw().putMulti(var6, (Collection)var7);
            }
         }

         return true;
      }
   }
}
