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
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oP;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.SetMap;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.auz;
import com.pnfsoftware.jebglobal.ava;
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

public class yt {
   private static final ILogger gp = GlobalLog.getLogger(yt.class);
   private Map oT = new HashMap();
   private Map fI = new HashMap();
   private SetMap WR = new SetMap();
   static yt.Sv pC = new yt.Sv();
   static yt.Sv A = new yt.Sv();
   static yt.Sv kS = new yt.Sv();
   static yt.Sv wS = new yt.Sv();
   static yt.Sv UT = new yt.Sv();
   static yt.Sv E = new yt.Sv();
   static yt.Sv sY = new yt.Sv();
   static yt.Sv ys = new yt.Sv();
   static Map ld = new HashMap();

   public Ws pC(String var1, boolean var2, CodelessLibraryID var3, CodelessLibraryVersion var4) throws JebException, IOException {
      List var5 = this.A(var1);
      if (var5.isEmpty()) {
         Object[] var11 = new Object[]{var1};
         return null;
      } else {
         Object[] var10000 = new Object[]{var5};
         Ws var6 = new Ws();
         ExecutableModelMetadata var7 = new ExecutableModelMetadata(var3, var4, 0);
         var6.pC(var7);

         for (File var9 : var5) {
            this.pC(var9, var6, var2);
            var10000 = new Object[]{var9.getName()};
         }

         this.pC(var6);
         return var6;
      }
   }

   private void pC(Ws var1) {
      this.UT(var1);
      this.E(var1);

      for (Func var3 : var1.wS().keySet()) {
         for (Func var5 : var1.wS().get(var3)) {
            var1.pC(var5.getModuleId(), var5);
         }
      }

      this.kS(var1);
      this.wS(var1);
      this.A(var1);
   }

   private void A(Ws var1) {
      for (rQ var3 : var1.A().keySet()) {
         for (Func var5 : var1.A().get(var3)) {
            var1.kS().put(var5, var3);
         }
      }
   }

   private void kS(Ws var1) {
      for (Func var3 : var1.wS().keySet()) {
         for (Func var5 : var1.wS().get(var3)) {
            var1.UT().put(var5, var3);
         }
      }
   }

   private void wS(Ws var1) {
      for (Func var3 : var1.E().keySet()) {
         for (Func var5 : var1.E().get(var3)) {
            var1.sY().put(var5, var3);
         }
      }
   }

   private void UT(Ws var1) {
      SetMap var2 = new SetMap();

      for (Func var4 : var1.wS().keySet()) {
         for (Func var6 : var1.wS().get(var4)) {
            if (var6.getModuleId().isUnknown()) {
               Func var7 = this.pC(var1, var6);
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

      var1.pC(var2);
   }

   private void E(Ws var1) {
      SetMap var2 = new SetMap();

      for (Func var4 : var1.E().keySet()) {
         for (Func var6 : var1.E().get(var4)) {
            if (var6.getModuleId().isUnknown()) {
               Func var7 = this.pC(var1, var6);
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

      var1.A(var2);
   }

   private Func pC(Ws var1, Func var2) {
      Assert.a(var2.getModuleId().isUnknown());
      Set var3 = this.WR.get(var2.getName());
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

         for (Entry var6 : this.oT.entrySet()) {
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
            this.pC(var12);
            var1.pC(var4.getId(), var12);
            return var12;
         } else {
            Object[] var13 = new Object[]{var2};
            return null;
         }
      }
   }

   private List A(String var1) {
      ArrayList var2 = new ArrayList();
      var2.addAll(IO.listFiles(var1));
      return var2;
   }

   protected Func pC(String var1, Module var2) {
      Set var3 = this.WR.get(var1);
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

   protected Set pC(String var1) {
      return this.WR.get(var1);
   }

   private void pC(Func var1) {
      this.WR.put(var1.getName(), var1);
   }

   protected String pC(INativeMethodItem var1) {
      String var2 = ((auu)var1).Er();
      return var2 != null ? var2 : var1.getName();
   }

   private boolean pC(File var1, Ws var2, boolean var3) throws JebException, IOException {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K("x86", 1, var3 ? 2 : 0, null, false);
      var4.pC(true);
      HeadlessClientContext var5 = oP.pC(var4);

      try {
         List var6 = oP.pC(var5, var1.getParent(), var1.getName());
         if (var6.size() != 1) {
            gp.error("Problem with root unit creation");
            return false;
         } else if (((IUnit)var6.get(0)).getChildren().size() == 0) {
            gp.error("No children units were created");
            return false;
         } else {
            ArrayList var7 = new ArrayList();
            String var8 = ((IUnit)var6.get(0)).getFormatType();
            if (var8.equals("ar")) {
               var7.addAll((Collection)((IUnit)var6.get(0)).getChildren().stream().filter(var0 -> var0 instanceof ICodeObjectUnit).collect(Collectors.toSet()));
            } else {
               if (!var8.equals("wincoff") && !var8.equals("elf") && !var8.equals("winpe")) {
                  gp.error("> non-suitable file format for model generation");
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
                     gp.error("Cannot find unique PBCU");
                     return false;
                  }

                  INativeCodeUnit var13 = (INativeCodeUnit)var14.get(0);
                  if (!var13.isProcessed()) {
                     try {
                        if (!var13.process()) {
                           gp.error("Problem while processing code unit!");
                           return false;
                        }
                     } catch (Exception var20) {
                        gp.error("Problem while processing code unit!");
                        return false;
                     }
                  }

                  var10.add(var13);
               }
            }

            this.pC(var10, var2, var3, true);
            return true;
         }
      } finally {
         oP.pC(var5, var4);
      }
   }

   public void pC(INativeCodeUnit var1, Ws var2, boolean var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add(var1);
      this.pC(var5, var2, var3, var4);
      this.pC(var2);
   }

   private void pC(List var1, Ws var2, boolean var3, boolean var4) {
      for (INativeCodeUnit var6 : var1) {
         this.pC(var6, var2, var3);
      }

      for (String var16 : this.WR.keySet()) {
         if (this.WR.get(var16).size() > 1) {
            Object[] var10000 = new Object[]{var16, this.WR.get(var16)};
         }
      }

      for (INativeCodeUnit var17 : var1) {
         this.A(var17, var2, var3, var4);
      }

      if (var3) {
         HashMap var15 = new HashMap();

         for (INativeCodeUnit var7 : var1) {
            for (INativeMethodItem var9 : var7.getInternalMethods()) {
               long var10 = this.A(var9);
               Object var12 = (List)var15.get(var10);
               if (var12 == null) {
                  var12 = new ArrayList();
                  var15.put(var10, var12);
               }

               var12.add((Func)this.fI.get(var9));
            }
         }

         SetMap var19 = var2.ld();

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

   private long A(INativeMethodItem var1) {
      long var2 = 0L;

      for (IInstruction var6 : var1.getData().getCFG().getInstructions()) {
         var2 += var6.toString().hashCode();
      }

      return var2;
   }

   private boolean pC(INativeCodeUnit var1, Ws var2, boolean var3) {
      Module var4 = Module.createKnownModule(new ModuleId(new File(var1.getParent().getName()).getName()));
      this.oT.put(var1, var4);

      for (INativeMethodItem var6 : var1.getInternalMethods()) {
         String var7 = this.pC(var6);
         Func var8 = Func.createFrom(var7, var4.getId());
         if (!var3) {
            var8.setAddresses(var6.getMemoryAddress(), var6.getData().getCFG().getFirstAddress(), var6.getData().getCFG().getEndAddress() - 1L);
         }

         this.pC(var8);
         this.fI.put((auu)var6, var8);
         var2.pC(var4.getId(), var8);
      }

      if (var1.getDetectedCompiler().isLinuxCompatible() && var1.getCodeObjectContainer() instanceof IELFUnit) {
         for (INativeMethodItem var12 : var1.getInternalMethods()) {
            Func var13 = (Func)this.fI.get(var12);
            if (var13 != null) {
               ISegmentInformation var14 = CodeObjectUnitUtil.findSectionByRelativeAddress(
                  var1.getCodeObjectContainer(), var12.getMemoryAddress() - var1.getVirtualImageBase()
               );
               if (var14 != null && var14.getName().equals(".plt")) {
                  INativeMethodItem var9 = var12.getData().getTrampolineTarget();
                  if (var9 != null && var9.getData() != null) {
                     Func var10 = (Func)this.fI.get(var9);
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

   private boolean A(INativeCodeUnit var1, Ws var2, boolean var3, boolean var4) {
      yt.Av var5 = new yt.Av((C)var1, var2, this, 2, 1);
      boolean var6 = this.pC(var1);
      if (!var5.pC(var3, var6, var3, true, true)) {
         gp.error("> problem while collecting string references");
      }

      if (!var5.pC(var3, var6)) {
         gp.error("> problem while collecting constant references");
      }

      if (var4) {
         if (!var5.A()) {
            gp.error("> problem while extracting call graph");
         }

         if (!var5.pC()) {
            gp.error("> problem while extracting reference graph");
         }
      }

      return true;
   }

   private boolean pC(INativeCodeUnit var1) {
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

   static {
      A.pC.add(Pattern.compile(".*openssl.*", 2));
      A.pC.add(Pattern.compile(".*ssl.*", 2));
      A.pC.add(Pattern.compile(".*crypto.*", 2));
      A.pC.add(Pattern.compile(".*\\.c$", 2));
      A.pC.add(Pattern.compile(".*ctx->.*"));
      kS.pC.add(Pattern.compile(".*curl.*", 2));
      kS.pC.add(Pattern.compile(".*\\.c$", 2));
      wS.pC.add(Pattern.compile(".*json.*", 2));
      wS.pC.add(Pattern.compile("double out of Int64 range"));
      wS.pC.add(Pattern.compile("double out of UInt64 range"));
      wS.pC.add(Pattern.compile("LargestInt out of UInt range"));
      wS.pC.add(Pattern.compile("LargestInt out of UInt64 range"));
      wS.pC.add(Pattern.compile("LargestUInt out of Int64 range"));
      wS.pC.add(Pattern.compile("LargestUInt out of UInt range"));
      wS.pC.add(Pattern.compile("Value is not convertible to Int64"));
      wS.pC.add(Pattern.compile("Value is not convertible to UInt"));
      wS.pC.add(Pattern.compile("Value is not convertible to UInt64"));
      wS.pC.add(Pattern.compile("double out of Int range"));
      wS.pC.add(Pattern.compile("double out of UInt range"));
      wS.pC.add(Pattern.compile("LargestInt out of Int range"));
      wS.pC.add(Pattern.compile("LargestUInt out of Int range"));
      wS.pC.add(Pattern.compile("Type is not convertible to string"));
      wS.pC.add(Pattern.compile("Value is not convertible to Int"));
      wS.pC.add(Pattern.compile("A valid JSON document must be either an array or an object value."));
      wS.pC.add(Pattern.compile("Exceeded stackLimit in readValue()."));
      wS.pC.add(Pattern.compile("Syntax error: value, object or array expected."));
      wS.pC.add(Pattern.compile("Missing ':' after object member name"));
      wS.pC.add(Pattern.compile("Missing ',' or '}' in object declaration"));
      wS.pC.add(Pattern.compile("Missing '}' or object member name"));
      wS.pC.add(Pattern.compile("Missing ',' or ']' in array declaration"));
      wS.pC.add(Pattern.compile("additional six characters expected to parse unicode surrogate pair."));
      wS.pC.add(Pattern.compile("Bad unicode escape sequence in string: four digits expected."));
      wS.pC.add(Pattern.compile("Bad unicode escape sequence in string: hexadecimal digit expected."));
      UT.kS = 8;
      E.pC.add(Pattern.compile(".*key .*", 2));
      E.pC.add(Pattern.compile(".*ssh.*", 2));
      sY.kS = 14;
      ys.pC.add(Pattern.compile(".*poco.*", 2));
      ld.put(CodelessLibraryID.OpenSSL, A);
      ld.put(CodelessLibraryID.libCURL, kS);
      ld.put(CodelessLibraryID.bzip2, UT);
      ld.put(CodelessLibraryID.libSSH2, E);
      ld.put(CodelessLibraryID.zlib, sY);
      ld.put(CodelessLibraryID.jsoncpp, wS);
      ld.put(CodelessLibraryID.poco, ys);
   }

   public static class Av {
      C pC;
      Ws A;
      yt kS;
      yt.Sv wS;
      Module UT;
      int E;
      int sY;
      Pattern ys = Pattern.compile("^([a-zA-Z]:\\\\).*");
      Pattern ld = Pattern.compile("^(\\.\\\\).*");
      Pattern gp = Pattern.compile("^(/).*");
      Pattern oT = Pattern.compile("^(\\./).*");
      Pattern fI = Pattern.compile(".*\\.c$");

      Av(C var1, Ws var2, yt var3, int var4, int var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.UT = (Module)var3.oT.get(var1);
         this.E = var4;
         this.sY = var5;
         this.wS = var2.fI() != null && yt.ld.containsKey(var2.fI().getLibraryId()) ? (yt.Sv)yt.ld.get(var2.fI().getLibraryId()) : yt.pC;
      }

      public boolean pC(boolean var1, boolean var2) {
         SortedMap var3 = this.pC
            .getCodeModel()
            .getItemsInRange(this.pC.getVirtualImageBase(), true, this.pC.getVirtualImageBase() + this.pC.getImageSize(), true, var0 -> var0 instanceof ava);
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
               INativeContinuousItem var10 = this.pC.getCodeModel().getNextItem(var7);
               if (var10 != null) {
                  long var11 = var10.getMemoryAddress();
                  var9 = (int)Math.min((long)var9, var11 - var7);
               }

               if (var9 >= this.sY) {
                  var13 = new byte[var9];

                  try {
                     this.pC.getMemory().read(var7, var9, var13, 0);
                     break;
                  } catch (MemoryException var25) {
                  }
               }
            }

            long var26 = EndianUtil.bytesToNumberUnsigned(this.pC.getProcessor().getEndianness().toByteOrder(), var13);
            if (var26 != 0L) {
               long var14 = this.pC.getVirtualImageBase();
               long var16 = this.pC.oT();
               if (var26 < var14 || var26 > var16) {
                  Set var18 = this.pC.getCodeModel().getReferenceManager().getReferencesTo(var7);
                  if (!var18.isEmpty()) {
                     K var19 = new K(var26, DH.pC);
                     HashSet var20 = new HashSet();

                     for (IReference var22 : var18) {
                        auu var23 = this.pC.pC(var22.getFrom().getInternalAddress(), false);
                        Func var24 = (Func)this.kS.fI.get(var23);
                        if (var24 != null) {
                           var20.add(var24);
                        }
                     }

                     if (!var20.isEmpty()) {
                        this.A.A().putMulti(var19, var20);
                     }
                  }
               }
            }
         }
      }

      public boolean pC(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5) {
         SortedMap var6 = this.pC
            .getCodeModel()
            .getItemsInRange(
               this.pC.getVirtualImageBase(), true, this.pC.getVirtualImageBase() + this.pC.getImageSize(), true, var0 -> var0 instanceof INativeDataItem
            );
         long var7 = this.pC.getVirtualImageBase();
         Iterator var9 = var6.entrySet().iterator();

         while (true) {
            long var12;
            String var14;
            DH var16;
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

               if (var12 != 0L && var2 && CodeObjectUnitUtil.findAllSymbolsByRelativeAddress((ICodeObjectUnit)this.pC.getParent(), var12 - var7).isEmpty()) {
                  var12 = 0L;
               }

               if (var12 != 0L
                  && var14 != null
                  && var4
                  && (
                     this.ys.matcher(var14).matches()
                        || this.ld.matcher(var14).matches()
                        || this.gp.matcher(var14).matches()
                        || this.oT.matcher(var14).matches()
                        || this.fI.matcher(var14).matches()
                  )) {
                  File var15 = new File(var14);
                  if (!var15.getName().isEmpty()) {
                     var14 = var15.getName();
                  }
               }

               if ((var14 == null || var14.length() >= this.E) && var12 != 0L && var14 != null) {
                  String var25 = var14.trim();
                  var16 = DH.pC;
                  if (!var1) {
                     break;
                  }

                  boolean var17 = false;

                  for (Pattern var19 : this.wS.A) {
                     if (var19.matcher(var25).matches()) {
                        var17 = true;
                        break;
                     }
                  }

                  if (!var17) {
                     Iterator var28 = this.wS.pC.iterator();

                     Pattern var30;
                     do {
                        if (!var28.hasNext()) {
                           break label122;
                        }

                        var30 = (Pattern)var28.next();
                     } while (!var30.matcher(var25).matches());

                     var16 = DH.kS;
                     break;
                  }
               }
            }

            RC var27 = new RC(var14, var16);
            Set var29 = this.pC.getCodeModel().getReferenceManager().getReferencesTo(var12);
            boolean var31 = false;
            if (!var29.isEmpty()) {
               if (this.wS.kS != 0 && var14.length() >= this.wS.kS) {
                  var16 = DH.kS;
                  var27 = new RC(var14, var16);
               }

               HashSet var20 = new HashSet();

               for (IReference var22 : var29) {
                  auu var23 = this.pC.pC(var22.getFrom().getInternalAddress(), false);
                  Func var24 = (Func)this.kS.fI.get(var23);
                  if (var24 != null) {
                     var20.add(var24);
                  }
               }

               if (!var20.isEmpty()) {
                  this.A.A().putMulti(var27, var20);
               }
            } else if (!var31 && var5) {
               if (var1 && var16 == DH.kS) {
                  this.A.gp().add(var27);
               } else if (!var1) {
                  this.A.gp().add(var27);
               }
            }
         }
      }

      public boolean pC() {
         IReferenceManager var1 = this.pC.getCodeModel().getReferenceManager();
         SetMap var2 = this.A.E();

         for (auu var4 : this.pC.getInternalMethods()) {
            Func var5 = (Func)this.kS.fI.get(var4);
            if (var5 == null) {
               throw new JebRuntimeException("cannot find ref func");
            }

            CFG var6 = var4.E().getCFG();

            for (long var8 : var6.getInstructionsMap().keySet()) {
               for (IReference var11 : (Set)var1.getReferencesFrom(var8)
                  .stream()
                  .filter(var0 -> var0.getTo().isInternalAddress() && var0.getType() == ReferenceType.GEN_DATA)
                  .collect(Collectors.toSet())) {
                  Func var12 = null;
                  if (this.pC.A(var11.getTo().getInternalAddress()) != null) {
                     auu var13 = this.pC.A(var11.getTo().getInternalAddress());
                     var12 = (Func)this.kS.fI.get(var13);
                     if (var12 == null) {
                        throw new JebRuntimeException("cannot find func");
                     }
                  } else if (this.pC.getNativeItemAt(var11.getTo().getInternalAddress()) instanceof auz) {
                     auu var14 = (auu)((auz)this.pC.getNativeItemAt(var11.getTo().getInternalAddress())).UO();
                     var12 = this.kS.pC(this.kS.pC(var14), this.UT);
                     if (var12 == null) {
                        Object[] var10000 = new Object[]{this.kS.pC(var14)};
                        var12 = Func.createUnknownFrom(this.kS.pC(var14));
                        this.kS.pC(var12);
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

      public boolean A() {
         ICallGraph var1 = this.pC.getCodeModel().getCallGraphManager().getGlobalCallGraph();
         long var2 = this.pC.getVirtualImageBase();

         for (auu var5 : this.pC.getInternalMethods()) {
            Func var6 = (Func)this.kS.fI.get(var5);
            if (var6 == null) {
               throw new JebRuntimeException("cannot find caller Func");
            }

            Object var7 = this.A.wS().get(var6);
            if (var7 == null) {
               var7 = new HashSet();
            }

            for (CallGraphVertex var9 : var1.getCallees(var5, false)) {
               if (var9.isInternal() || var9.getDereferencedAddress() != -1L) {
                  long var18 = var9.isInternal() ? var9.getInternalAddress().getAddress() : var9.getDereferencedAddress();
                  auu var13 = this.pC.A(var18);
                  Func var17;
                  if (var13 == null) {
                     List var14 = CodeObjectUnitUtil.findAllSymbolsByRelativeAddress((ICodeObjectUnit)this.pC.getParent(), var18 - var2);
                     if (var14.size() < 1) {
                        Object[] var23 = new Object[]{this.kS.pC(var5), var18};
                        continue;
                     }

                     if (var14.size() > 1) {
                        Object[] var20 = new Object[]{var18, var14};
                     }

                     String var15 = ((ISymbolInformation)var14.get(0)).getName();
                     Set var16 = this.kS.pC(var15);
                     if (var16 != null && !var16.isEmpty()) {
                        if (var16.size() > 1) {
                           Object[] var22 = new Object[]{var18, this.kS.pC(var5)};
                        }

                        var17 = (Func)var16.iterator().next();
                     } else {
                        Object[] var21 = new Object[]{var15};
                        var17 = Func.createUnknownFrom(var15);
                        this.kS.pC(var17);
                     }
                  } else {
                     var17 = (Func)this.kS.fI.get(var13);
                  }

                  if (var17 != null) {
                     var7.add(var17);
                  }
               } else if (var9.getRoutine() == null) {
                  Object[] var19 = new Object[]{var9};
               } else {
                  Set var11 = this.kS.pC(this.kS.pC(var9.getRoutine()));
                  if (var11 != null && !var11.isEmpty()) {
                     if (var11.size() > 1) {
                        Object[] var10000 = new Object[]{var9.getRoutine()};
                     }

                     Func var10 = (Func)var11.iterator().next();
                     var7.add(var10);
                  } else {
                     Func var12 = Func.createUnknownFrom(this.kS.pC(var9.getRoutine()));
                     this.kS.pC(var12);
                     var7.add(var12);
                  }
               }
            }

            if (!var7.isEmpty()) {
               this.A.wS().putMulti(var6, (Collection)var7);
            }
         }

         return true;
      }
   }

   private static class Sv {
      List pC = new ArrayList();
      List A = new ArrayList();
      int kS = 0;
   }
}
