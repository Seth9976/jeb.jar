package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuClass;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmuUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryChangesRecorder;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.IniFileEditor;
import com.pnfsoftware.jeb.util.collect.BiMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class cjo {
   static final ILogger q = GlobalLog.getLogger(cjo.class);
   private static final boolean mI = false;
   static int RF = 100000000;
   INativeCodeUnit xK;
   ITypeManager Dw;
   ICallingConvention Uv;
   IProcessor oW;
   ProcessorType gO;
   int nf;
   int gP;
   INativeDecompilerUnit za;
   IEGlobalContext lm;
   Map zz = new HashMap();
   public cjq JY;
   public cjx HF;
   long LK;
   long io;
   IDState qa;
   private boolean jq;
   EEmulator Hk;
   INativeType Me;
   INativeType PV;
   INativeType oQ;
   long xW;
   long KT;
   long Gf;
   long Ef;
   long cC;
   private long ui;
   private long TX;
   private BiMap Rr = new BiMap();
   private BiMap EB = new BiMap();
   private int Xo = 1;
   private BiMap Bu = new BiMap();
   private int IN = 1;
   private BiMap rL = new BiMap();
   private BiMap eJ = new BiMap();
   private cjs YN;
   private int Rv;
   private Map zx = new HashMap();
   int sH;
   cjn CE;
   cjw wF;
   long If = -1L;
   long Dz = -1L;
   private MemoryChangesRecorder.Results ZT;
   private static final String Ri = cvm.q(new byte[]{-62, 0, 47, 28, 24, 43, 45, 23, 22, 28, 3, 26, 19, 23}, 1, 157);
   private static final String GY = cvm.q(new byte[]{46, 0, 6, 16}, 2, 37);
   private static final String Wx = cvm.q(new byte[]{33, 27, 25}, 2, 206);

   static void q(String var0, Object... var1) {
   }

   public cjo(INativeCodeUnit var1) {
      this(var1, null, null);
   }

   public cjo(INativeCodeUnit var1, INativeDecompilerUnit var2, EEmulator var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var1;
         if (var2 == null) {
            var2 = var1.getDecompiler();
         }

         this.za = var2;
         if (var3 == null) {
            var3 = EEmulator.createStandard(var2.getGlobalContext(), RF);
         }

         this.Hk = var3;
         this.JY();
      }
   }

   public cjo(IDState var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.qa = var1;
         IDexDecompilerUnit var2 = var1.getGlobalContext().getDecompiler();
         INativeCodeUnit var4 = (INativeCodeUnit)var2.getUnitProcessor().process("unipbcu", new BytesInput(new byte[0]), var2, "arm64", true, true);
         var4.setSubsystemType(SubsystemType.LINUX);
         var4.setCompilerType(CompilerType.GCC);
         var4.process();
         var4.getTypeLibraryService().load(ProcessorType.ARM64, 3);
         if (var1.getEmulatedEnvironment() != null) {
            this.CE = (cjn)var1.getEmulatedEnvironment().getInternalInfo();
         } else {
            this.CE = cjn.q(var2.getParentProject());
         }

         this.q(var4.getMemory());
         this.xK = var4;
         this.za = var4.getDecompiler();
         this.Hk = EEmulator.createStandard(this.za.getGlobalContext(), RF);
         this.JY();
      }
   }

   private void JY() {
      this.Dw = this.xK.getTypeManager();
      this.Uv = this.Dw.getCallingConventionManager().getDefaultConvention();
      this.oW = this.xK.getProcessor();
      this.gO = this.oW.getType();
      this.nf = this.Dw.getPointerSize();
      this.gP = this.Dw.getSlotSize();
      this.lm = this.za.getGlobalContext();
      IVirtualMemory var1 = this.Hk.getVirtualMemory();
      this.ui = VirtualMemoryUtil.allocate(var1, 2063597568L, 8192, 7);
      this.TX = this.ui;
      this.JY = new cjq(var1);
      if (this.xW == 0L) {
         this.xW = EEmuUtil.setupGenericThreadLocalStorageInfo(this.Hk, 2130706432L, true);
      } else {
         EEmuUtil.setupGenericThreadLocalStorageInfo(this.Hk, this.xW, false);
      }

      this.HF = this.HF();
      this.LK = 4294967296L;
      this.HF
         .q(
            var0 -> cvm.q(25, 3432751, -969342651, var0.nf)
               || var0.nf.contains(cvm.q(new byte[]{108, 3, 25, 27, 17, 71, 20, 7}, 2, 236))
               || var0.nf.contains(cvm.q(new byte[]{-79, 67, 5, 11, 1, 72, 0, 5, 93, 28}, 1, 158))
         );
      String var2 = this.gO();
      this.HF.q(490673381376L, 490673647616L, 9, 0L, var2);
      this.RF(var1);
      this.wF = new cjw(this);
   }

   public void q() throws EvaluationException {
      TypeLibraryService var1 = this.xK.getTypeLibraryService();
      this.Me = var1.findTypeBySignature(cvm.q(new byte[]{9, 14, 6, 24, 36, 36}, 2, 128), null);
      this.PV = var1.findTypeBySignature(cvm.q(new byte[]{-83, 4, 7, 12, 43, 24}, 1, 231), null);
      this.oQ = var1.findTypeBySignature(cvm.q(new byte[]{9, 33, 57, 55, 19, 29, 14, 30, 17, 109, 77, 23, 65, 79, 85}, 2, 218), null);
      this.YN = new cjs(this);
      IStructureType var2 = (IStructureType)((IReferenceType)TypeUtil.getNonAlias(this.Me, IReferenceType.class)).getPointedType();
      IStructureType var3 = (IStructureType)((IReferenceType)TypeUtil.getNonAlias(this.PV, IReferenceType.class)).getPointedType();
      IStructureType[] var4 = new IStructureType[]{var2, var3};

      for (IStructureType var8 : var4) {
         for (IStructureTypeField var10 : var8.getFields()) {
            INativeType var11 = TypeUtil.getNonAlias(var10.getType());
            if (TypeUtil.isFunctionPointer(var11)) {
               IPrototypeItem var12 = (IPrototypeItem)((IReferenceType)var11).getPointedType();
               this.q(var10.getName(), var12);
            }
         }
      }

      IVirtualMemory var16 = this.Hk.getVirtualMemory();
      long var17 = VirtualMemoryUtil.allocate(var16, this.ui + 65536L, 4096, 7);
      long var18 = var17;

      try {
         this.KT = var18;
         this.Gf = this.KT + this.nf;
         var16.writePointer(var18, this.Gf);
         var18 = this.Gf;

         for (IStructureTypeField var25 : var2.getFields()) {
            INativeType var27 = TypeUtil.getNonAlias(var25.getType());
            if (TypeUtil.isFunctionPointer(var27)) {
               long var13 = (Long)this.Rr.getKeyForValue(var25.getName());
               var16.writePointer(var18 + var25.getOffset(), var13);
            }
         }

         var18 += var2.getSize();
         this.Ef = var18;
         this.cC = this.Ef + this.nf;
         var16.writePointer(var18, this.cC);
         var18 = this.cC;

         for (IStructureTypeField var26 : var3.getFields()) {
            INativeType var28 = TypeUtil.getNonAlias(var26.getType());
            if (TypeUtil.isFunctionPointer(var28)) {
               long var29 = (Long)this.Rr.getKeyForValue(var26.getName());
               var16.writePointer(var18 + var26.getOffset(), var29);
            }
         }

         var3.getSize();
      } catch (MemoryException var15) {
         throw new RuntimeException(var15);
      }

      this.io = this.q(cvm.q(new byte[]{104, 0, 47, 28, 24, 43, 45, 23, 22, 28, 3, 26, 19, 23}, 1, 55), null);
      Object[] var10000 = new Object[]{this.io};
      if (this.qa != null) {
         String var24 = DexUtil.findPackageName(this.qa.getGlobalContext().getDex());
         if (var24 != null) {
            this.Hk.getMetadata().processName = var24.substring(1);
         }
      }

      this.Hk.registerHooks(new cjt(this), true);
      this.Hk.registerHooks(this.YN, true);
      this.Hk.registerHooks(new cjr(this), true);
      this.Hk.registerHooks(new cjo.eo(), true);
      this.Hk.setup();
   }

   private void q(IVirtualMemory var1) {
      if (this.CE != null) {
         if (this.xW == 0L) {
            cka var2 = new cka(this.CE);

            try {
               var1.setLazyMemoryProvider(var2, true);
            } catch (Exception var6) {
               q.catchingSilent(var6);
            }

            String var3;
            try {
               cjn.CU var4 = this.CE.RF(4);
               var3 = Strings.decodeUTF8(this.CE.q(var4));
            } catch (IOException var5) {
               throw new RuntimeException(var5);
            }

            IniFileEditor var7 = new IniFileEditor(var3, '#');
            this.xW = Conversion.stringToLong(var7.getValue(cvm.q(new byte[]{23, 63, 57, 61, 32, 54, 34, 36, 68}, 2, 74)));
         }
      }
   }

   private cjx HF() {
      cjx var1 = new cjx();
      if (this.CE != null) {
         try {
            cjn.CU var2 = this.CE.RF(2);
            String var3 = Strings.decodeUTF8(this.CE.q(var2));
            var1 = cjx.RF(var3);
         } catch (IOException var4) {
            q.catching(var4);
         }
      }

      return var1;
   }

   private void RF(IVirtualMemory var1) {
      System.currentTimeMillis();

      for (cjx.eo var3 : this.HF.xK().values()) {
         if (var3.Dw == 0L && var3.nf.endsWith(".so")) {
            this.JY.q(var3.q, true);
         }
      }

      System.currentTimeMillis();
   }

   public void q(IDState var1) {
      if (var1 == null) {
         throw new IllegalArgumentException(
            cvm.q(new byte[]{-25, 56, 6, 7, 84, 80, 2, 29, 25, 31, 13, 1, 69, 65, 65, 68, 1, 29, 28, 1, 6, 67, 69, 8, 24, 84}, 1, 170)
         );
      } else if (this.jq) {
         throw new IllegalArgumentException(
            cvm.q(new byte[]{-52, 59, 0, 79, 76, 13, 21, 17, 69, 84, 27, 79, 83, 22, 17, 84, 65, 65, 68, 1, 29, 28, 1, 6, 67, 69, 8, 24, 84}, 1, 152)
         );
      } else {
         this.qa = var1;
      }
   }

   public IDState RF() {
      return this.qa;
   }

   public IEmulatedAndroid xK() {
      return this.qa == null ? null : this.qa.getEmulatedEnvironment();
   }

   void Dw() {
      if (this.qa == null) {
         throw new IllegalStateException(cvm.q(new byte[]{13, 10, 21, 29, 1, 73, 3, 13, 12, 68, 77, 0, 9, 69, 92, 76, 85, 82, 88, 79, 64}, 2, 230));
      }
   }

   private long q(String var1, IPrototypeItem var2) {
      Long var3 = (Long)this.Rr.getKeyForValue(var1);
      if (var3 != null) {
         return var3;
      } else {
         var3 = this.TX;
         this.Rr.put(var3, var1);
         this.TX += 16L;
         return var3;
      }
   }

   private void q(int var1, String var2) {
      this.EB.put(var1, var2);
   }

   String q(int var1) {
      String var2 = (String)this.EB.get(var1);
      if (var2 == null) {
         try {
            this.Dw();
            var2 = ((IDEmuClass)this.qa.getObject(var1)).getFullName();
         } catch (DexDecEvaluationException var4) {
            throw new cju(
               cvm.q(
                     new byte[]{
                        37,
                        34,
                        15,
                        0,
                        1,
                        27,
                        84,
                        82,
                        23,
                        17,
                        6,
                        27,
                        12,
                        19,
                        19,
                        69,
                        69,
                        8,
                        24,
                        25,
                        13,
                        21,
                        17,
                        1,
                        68,
                        67,
                        15,
                        13,
                        18,
                        0,
                        83,
                        70,
                        9,
                        29,
                        82,
                        67,
                        15,
                        22,
                        19,
                        13,
                        68
                     },
                     1,
                     102
                  )
                  + var1,
               var4
            );
         } catch (Exception var5) {
            q.catchingSilent(var5);
         }

         this.q(var1, var2);
      }

      return var2;
   }

   int q(String var1) throws DexDecEvaluationException {
      Integer var2 = (Integer)this.EB.getKeyForValue(var1);
      if (var2 == null) {
         if (this.qa == null) {
            this.jq = true;
            var2 = 1 + this.EB.size();
         } else {
            var2 = this.qa.getClassReference(var1).getObjectReferenceId();
         }

         this.q(var2, var1);
      }

      return var2;
   }

   int RF(String var1) {
      Integer var2 = (Integer)this.rL.getKeyForValue(var1);
      if (var2 == null) {
         var2 = this.IN++;
         this.rL.put(var2, var1);
      }

      return var2;
   }

   String RF(int var1) {
      return (String)this.rL.get(var1);
   }

   int xK(String var1) {
      Integer var2 = (Integer)this.Bu.getKeyForValue(var1);
      if (var2 == null) {
         var2 = this.Xo++;
         this.Bu.put(var2, var1);
      }

      return var2;
   }

   String xK(int var1) {
      return (String)this.Bu.get(var1);
   }

   void q(String var1, String var2, String var3, long var4) {
      if (!var1.startsWith("[") && !var1.startsWith("L")) {
         var1 = "L" + var1 + ";";
      }

      String var6 = var1 + "->" + var2 + var3;
      Object[] var10000 = new Object[]{var6, var4};
      if (this.eJ.put(var4, var6) != null) {
         var10000 = new Object[0];
      }

      this.Hk.monitorHLSpecial(10, var6, var4);
   }

   int Dw(String var1) {
      ArrayList var2 = new ArrayList();

      for (String var4 : this.eJ.values()) {
         if (var4.startsWith(var1)) {
            var2.add(var4);
         }
      }

      for (String var7 : var2) {
         Long var5 = (Long)this.eJ.removeValue(var7);
         if (var5 != null) {
            this.Hk.monitorHLSpecial(11, var7, var5);
         }
      }

      return var2.size();
   }

   public String Uv() {
      if (this.xK() != null) {
         return this.xK().getPackageName();
      } else {
         if (this.qa != null) {
            String var1 = DexUtil.findPackageName(this.qa.getGlobalContext().getDex());
            if (var1 != null) {
               return var1;
            }
         }

         return "com.somename";
      }
   }

   public String oW() {
      if (this.xK() != null) {
         return this.xK().getAppFolder();
      } else {
         String var1 = cvm.q(
            new byte[]{
               -38,
               75,
               5,
               21,
               21,
               78,
               78,
               17,
               0,
               95,
               81,
               0,
               14,
               57,
               36,
               5,
               36,
               30,
               7,
               103,
               93,
               89,
               5,
               101,
               47,
               75,
               88,
               93,
               64,
               59,
               15,
               47,
               47,
               36,
               90,
               0,
               18,
               10,
               86,
               94,
               119,
               34,
               31,
               40,
               27,
               121,
               99,
               47,
               27,
               41,
               36,
               15,
               12,
               65,
               93,
               31,
               40,
               13,
               28,
               99,
               108,
               16,
               108,
               0
            },
            1,
            245
         );
         return Strings.ff(var1, this.Uv());
      }
   }

   public String gO() {
      return this.xK() != null ? this.xK().getApkPath() : this.oW() + cvm.q(new byte[]{108, 13, 17, 10, 23, 71, 6, 24, 31}, 2, 140);
   }

   public String nf() {
      return this.Uv();
   }

   public MemoryChangesRecorder.Results gP() {
      return this.ZT;
   }

   public long q(IELFUnit var1, boolean var2) {
      if (var1.getHeader().getType() != 3) {
         throw new IllegalArgumentException(
            cvm.q(
               new byte[]{
                  6,
                  23,
                  0,
                  28,
                  17,
                  29,
                  14,
                  6,
                  19,
                  0,
                  73,
                  67,
                  90,
                  72,
                  80,
                  75,
                  92,
                  87,
                  12,
                  79,
                  80,
                  90,
                  87,
                  87,
                  88,
                  0,
                  103,
                  33,
                  46,
                  74,
                  76,
                  9,
                  73,
                  3,
                  28,
                  5,
                  93,
                  22
               },
               2,
               95
            )
         );
      } else {
         String var3 = var1.getName();
         Long var4 = (Long)this.zz.get(var3);
         if (var4 != null) {
            return var4;
         } else {
            EState var5 = this.Hk.getState();
            IVirtualMemory var6 = this.Hk.getVirtualMemory();
            long var7 = var1.getLoaderInformation().getImageSize();
            var4 = VirtualMemoryUtil.findAvailableRange(var6, this.LK, (int)var7);
            var1.map(var6, var4, true, new cjp(this));
            long var9 = var6.roundToSize(var4 + var7);
            cjq.CU var11 = this.JY.q(var4, true);
            String var12 = cvm.q(new byte[]{108, 3, 25, 27, 93, 8, 21, 5, 66, 20, 7}, 2, 2);
            String var13 = this.oW() + var12 + var3;
            this.HF.q(var4, var9, 5, 0L, var13);
            long var14 = var11.xK();

            try {
               var6.writePointer(var14 + 8L, var4);
               var6.writePointer(var14 + 16L, this.io);
            } catch (MemoryException var25) {
               throw new RuntimeException(var25);
            }

            ArrayList var16 = new ArrayList();

            for (long var18 : var11.gO()) {
               if (var18 != 0L && var18 != -1L) {
                  var16.add(var18);
               }
            }

            MemoryChangesRecorder var28 = null;
            if (var2) {
               var28 = new MemoryChangesRecorder(var6);
               var28.setup();
            }

            try {
               if (!var16.isEmpty()) {
                  IPrototypeItem var29 = TypeUtil.buildQuickPrototype(this.Dw, "void()");

                  for (long var20 : var16) {
                     this.Hk.setGlobalRoutineEmulation(var20, var29);
                     this.Hk.run();
                  }
               }

               long var30 = var11.q(cvm.q(new byte[]{-126, 4, 7, 22, 16, 33, 34, 35, 14, 5}, 1, 200));
               if (var30 != 0L) {
                  IPrototypeItem var31 = TypeUtil.buildQuickPrototype(
                     this.Dw, cvm.q(new byte[]{41, 6, 30, 13, 90, 35, 6, 30, 21, 118, 101, 73, 5, 0, 71, 86, 80, 87, 6, 9}, 2, 36)
                  );
                  this.Hk.setGlobalRoutineEmulation(var30, var31);
                  this.Hk.addArgument(this.KT, this.Me.getReference());
                  this.Hk.addArgument(0L, this.Dw.getVoidReference());
                  boolean var21 = false;
                  var5.enableMemoryWriteHitmap(var21, false);
                  this.Hk.run();
                  if (var21) {
                     var5.getMemoryWriteHitmap();
                  }
               }
            } finally {
               if (var28 != null) {
                  this.ZT = var28.getMemoryChanges();
                  var28.teardown();
               }
            }

            return var4;
         }
      }
   }

   public Long q(String var1, boolean var2, long var3, int var5, List var6) {
      JvmMethodSig var7 = JvmMethodSig.parse(var1);
      IPrototypeItem var8 = (IPrototypeItem)this.zx.get(var1);
      if (var8 == null) {
         INativeType var9 = this.Dw.getType(cjv.q(var7.rettype));
         ArrayList var10 = new ArrayList();
         var10.add(this.PV.getReference());
         var10.add(
            var2 ? this.Dw.getType(cvm.q(new byte[]{41, 12, 28, 24, 1, 26}, 2, 25)) : this.Dw.getType(cvm.q(new byte[]{41, 0, 18, 19, 23, 10, 19}, 2, 105))
         );

         for (String var12 : var7.partypes) {
            var10.add(this.Dw.getType(cjv.q(var12)));
         }

         var8 = this.Dw.createPrototype(var9, var10);
         this.zx.put(var1, var8);
      }

      List var14 = var8.getParameterTypes();
      this.Hk.setGlobalRoutineEmulation(var3, var8);
      this.Hk.addArgument(this.Ef, (INativeType)var14.get(0));
      this.Hk.addArgument(var5, (INativeType)var14.get(1));
      int var15 = 2;

      for (long var18 : var6) {
         this.Hk.addArgument(var18, (INativeType)var14.get(var15));
         var15++;
      }

      this.Hk.run();
      if (var7.rettype.equals("V")) {
         return null;
      } else {
         TypeLayoutInfo var17 = TypeUtil.getLayoutInfo(var8.getReturnType());
         IEImm var19 = this.Hk.readStorage(var8.getCallingConvention().getOutput(var17, 0));
         return var19.getValue().longValue();
      }
   }

   public EEmulator za() {
      return this.Hk;
   }

   public cjw lm() {
      return this.wF;
   }

   public Map zz() {
      return this.eJ.asReverseMap();
   }

   public Long Uv(String var1) {
      return (Long)this.eJ.getKeyForValue(var1);
   }

   public int q(boolean var1) {
      int var2 = this.sH;
      if (var1) {
         this.sH = 0;
      }

      return var2;
   }

   private String q(Map var1) {
      StringBuilder var2 = new StringBuilder();
      TreeSet var3 = new TreeSet(var1.keySet());
      Long var4 = (Long)var3.first();

      while (var4 != null) {
         long var5 = var4 + 1L;

         while (var3.contains(var5)) {
            var5++;
         }

         if (var4 < var5) {
            Strings.ff(var2, "- 0x%X -> 0x%X (%d)\n", var4, var5, var5 - var4);
         } else {
            Strings.ff(var2, "- 0x%X\n", var4);
         }

         var4 = (Long)var3.higher(var5);
      }

      return var2.toString();
   }

   private int LK() throws MemoryException {
      EState var1 = this.Hk.getState();
      IVirtualMemory var2 = this.Hk.getVirtualMemory();
      if (this.gO == ProcessorType.ARM64) {
         long var3 = this.Hk.readStorage(StorageEntry.createStackSlot(0L)).getValueAsAddress();
         long var5 = this.Hk.readStorage(StorageEntry.createStackSlot(1L)).getValueAsAddress();
         long var7 = var1.getValueAsLongSafe(this.lm.getVariableByName("X16").getId());
         long var9 = var2.readPointer(var7 - 8L);
         cjq.CU var11 = this.JY.q(var9, true);
         if (var11 == null) {
            return 0;
         } else {
            cjq.nI var12 = var11.q(var3);
            if (var12 == null) {
               return 0;
            } else {
               String var13 = var12.q();
               cjq.eo var14 = this.JY.xK(var13);
               long var15;
               byte var17;
               if (var14 == null) {
                  var15 = this.Hk.createPseudoRoutine(var13);
                  var17 = 1;
               } else if (!var14.oW()) {
                  var15 = var14.q();
                  this.Hk.registerRoutine(var15, var13);
                  var17 = 2;
               } else {
                  long var18 = var14.q();

                  try {
                     EEmulator var20 = EEmulator.createStandard(this.lm, 100);
                     IPrototypeItem var21 = TypeUtil.buildQuickPrototype(
                        this.Dw, cvm.q(new byte[]{53, 0, 25, 29, 88, 65, 11, 7, 26, 71, 4, 67, 69, 79, 95, 94, 19, 26}, 2, 232)
                     );
                     var20.setGlobalRoutineEmulation(var18, var21);
                     var20.setup();
                     long var22 = 4611686018427387904L;
                     long var24 = var20.getSPAddress() - 24L;
                     var20.getVirtualMemory().writeLong(var24 + 0L, 24L);
                     var20.getVirtualMemory().writeLong(var24 + 8L, 0L);
                     var20.getVirtualMemory().writeLong(var24 + 16L, 0L);
                     var20.addArgument(var22, this.Dw.getInteger(8, false));
                     var20.addArgument(var24, this.Dw.getInteger(8, false));
                     var20.setReturnAddress(2864434397L);
                     var20.run();
                     var15 = var20.getReturnValue().getValueAsAddress();
                     this.Hk.registerRoutine(var15, var13);
                     var17 = 2;
                  } catch (Exception var26) {
                     q.catching(var26);
                     var15 = this.Hk.createPseudoRoutine(var13);
                     var17 = 1;
                  }
               }

               this.Hk.writePointer(var3, var15);
               this.Hk.updateSPAddress(16);
               var1.setValue(this.lm.getVariableByName("X30"), var5);
               this.Hk.setPCAddress(var15);
               return var17;
            }
         }
      } else {
         return 0;
      }
   }

   class eo implements IEEmulatorHooks {
      @Override
      public Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
         String var5 = (String)cjo.this.Rr.get(var2);
         if (var5 == null) {
            return null;
         } else if (var5.equals(cjo.Ri)) {
            try {
               int var6 = cjo.this.LK();
               if (var6 != 0) {
                  return true;
               }
            } catch (MemoryException var7) {
            }

            return false;
         } else if (cjo.this.YN != null) {
            cjo.this.Rv++;
            var1.hooksEvaluateExternal(var5, null);
            return true;
         } else {
            return null;
         }
      }

      @Override
      public Boolean evaluateUntranslated(EEmulator var1, IEUntranslatedInstruction var2, IInstruction var3) {
         if (cjo.this.gO == ProcessorType.ARM64) {
            long var4 = var1.getPCAddress();
            String var6 = var3.getMnemonic().toLowerCase();
            if (var6.equals(cjo.GY)) {
               IEVar var7 = (IEVar)var2.getParameterExpression(0);
               IEGeneric var8 = var2.getParameterExpression(1);
               if (EUtil.isZero(var8)) {
                  var1.getState().setValue(var7, EUtil.zero(var7.getBitsize()));
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               }
            } else if (var6.equals(cjo.Wx)) {
               var1.setPCAddress(var4 + var3.getSize());
               return true;
            }
         }

         return null;
      }
   }
}
