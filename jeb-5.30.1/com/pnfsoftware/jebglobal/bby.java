package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.PassthroughUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.items.IFieldManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackage;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackageManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IVirtualTableDefinition;
import com.pnfsoftware.jeb.core.units.code.asm.type.IllegalTypeNameException;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParser;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.ISimpleFilter;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Ser
public class bby implements IFieldManager, INativeItemListener, IClassManager, IPackageManager, ITypeManager {
   private static final ILogger q = GlobalLog.getLogger(bby.class);
   @SerId(1)
   private bbr RF;
   @SerId(2)
   private int xK;
   @SerId(3)
   private bbg Dw;
   @SerId(4)
   private Map Uv = new ConcurrentHashMap();
   @SerId(5)
   private IdentityHashMap oW = new IdentityHashMap();
   @SerId(6)
   private IdentityHashMap gO = new IdentityHashMap();
   @SerId(7)
   private List nf = new CopyOnWriteArrayList();
   @SerId(8)
   private List gP = new CopyOnWriteArrayList();
   @SerId(9)
   private IdentityHashMap za = new IdentityHashMap();
   @SerId(10)
   private bbo lm;
   @SerId(11)
   private aam zz;
   @SerId(12)
   private ProcessorType JY;
   @SerId(13)
   private int HF;
   @SerId(15)
   private Map LK = new ConcurrentHashMap();
   @SerId(16)
   private int io;
   @SerId(17)
   private Map qa = new ConcurrentHashMap();
   @SerId(18)
   private int Hk;
   @SerId(19)
   private Map Me = new ConcurrentHashMap();
   @SerId(20)
   private int PV;
   @SerId(21)
   private Map oQ = new ConcurrentHashMap();
   @SerId(22)
   private int xW;
   @SerId(23)
   private IUnitLock KT;
   @SerId(24)
   private SubsystemType Gf;
   @SerId(25)
   private CompilerType Ef;
   @SerId(26)
   private int cC;
   @SerId(27)
   private int sH;
   @SerId(28)
   private Map CE = new ConcurrentHashMap();
   @SerTransient
   private List wF;
   @SerTransient
   private AtomicBoolean If;
   @SerTransient
   private bbt Dz;
   @SerTransient
   private Map mI = new ConcurrentHashMap();
   @SerTransient
   private TypeLibraryService jq;

   @SerCustomInitPostGraph
   private void Me() {
      this.wF = new CopyOnWriteArrayList();
      this.If = new AtomicBoolean(true);
      bbd var1 = this.Uv("void");
      this.Dz = var1 == null ? null : var1.Uv();
      if (this.cC == 0) {
         this.cC = this.xK;
      }
   }

   public bby(aam var1, ProcessorType var2, SubsystemType var3, CompilerType var4, IPrimitiveSizes var5, int var6, int var7, String var8) {
      if (var1 instanceof INativeCodeUnit) {
         this.KT = ((INativeCodeUnit)var1).getLock();
         Assert.a(this.KT != null);
      }

      this.zz = var1;
      this.JY = var2;
      this.Gf = var3;
      this.Ef = var4;
      this.Dw = new bbg(var2, var3, var4, var8);
      if (var5 == null) {
         var5 = PrimitiveSizes.getCommon(var2, var4);
      }

      if (var6 == 0) {
         var6 = var2.is64Bit() ? 8 : 4;
      }

      if (var7 == 0) {
         var7 = var6;
      }

      this.xK = var6;
      this.cC = var7;
      this.RF = new bbr(this, (IPrimitiveSizes)var5, var6);
      this.lm = this.q(null, null);
      this.Me();
   }

   public static bby q(ProcessorType var0, SubsystemType var1, CompilerType var2, String var3) {
      return new bby(null, var0, var1, var2, null, 0, 0, var3);
   }

   public static bby q(ProcessorType var0, SubsystemType var1, CompilerType var2) {
      return new bby(null, var0, var1, var2, null, 0, 0, null);
   }

   public static bby q(ProcessorType var0) {
      return q(var0, null, null);
   }

   public static bby q() {
      return q(ProcessorType.X86, SubsystemType.WINDOWS_USER, CompilerType.MSVC);
   }

   public static bby RF() {
      return q(ProcessorType.X86_64, SubsystemType.WINDOWS_USER, CompilerType.MSVC);
   }

   @Override
   public ProcessorType getProcessorType() {
      return this.JY;
   }

   @Override
   public SubsystemType getSubsystemType() {
      return this.Gf;
   }

   @Override
   public CompilerType getCompilerType() {
      return this.Ef;
   }

   @Override
   public int getPointerSize() {
      return this.xK;
   }

   @Override
   public int getSlotSize() {
      return this.cC;
   }

   public bbr xK() {
      return this.RF;
   }

   public bbg Dw() {
      return this.Dw;
   }

   public void q(TypeLibraryService var1) {
      this.jq = var1;
   }

   @Override
   public TypeLibraryService getTypeLibraryService() {
      return this.jq;
   }

   public boolean Uv() {
      return this.zz == null;
   }

   @Override
   public TypeStringParser getParser() {
      return new TypeStringParser(this);
   }

   public IUnitLock oW() {
      return (IUnitLock)(this.KT != null ? this.KT : PassthroughUnitLock.getInstance());
   }

   public void q(aam var1) {
      this.zz = var1;
   }

   public INativeItemListener gO() {
      return this.zz;
   }

   @Override
   public void addListener(INativeItemListener var1) {
      if (var1 != null) {
         this.wF.add(var1);
      }
   }

   @Override
   public void removeListener(INativeItemListener var1) {
      if (var1 != null) {
         this.wF.remove(var1);
      }
   }

   public void q(NativeItemEvent var1) {
      if (this.If.get()) {
         if (this.zz != null) {
            this.zz.onNativeItemEvent(var1);
         }

         for (INativeItemListener var3 : this.wF) {
            if (var3 != var1.getItem()) {
               var3.onNativeItemEvent(var1);
            }
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof bbn) {
         bbn var2 = (bbn)var1.getItem();
         if (var2.xK() == this && ((axj)var2).isDisposed()) {
            ((axj)var2).removeListener(this);
         }
      }

      this.q(var1);
   }

   private String q(String var1, List var2, boolean var3) {
      this.oW().verifyLocked();
      if (var1 == null) {
         var1 = "__anon_" + Strings.randomUniqueId();
      }

      var1 = TypeUtil.preNormalizeSignature(var1);
      if (var1.endsWith("*")) {
         throw new IllegalTypeNameException("Signature is a reference type", var1);
      } else if (var1.endsWith("]")) {
         throw new IllegalTypeNameException("Signature is an array type", var1);
      } else {
         String var4 = TypeUtil.normalizeSignature(true, var1, var2, null);
         if (var4 == null) {
            throw new IllegalTypeNameException("Invalid signature", var1);
         } else if (!var3 && this.q(var4, false) != null) {
            throw new IllegalTypeNameException("Type already exists", var4);
         } else {
            return var4;
         }
      }
   }

   private String q(String var1, List var2) {
      return this.q(var1, var2, false);
   }

   private bbx q(String var1, List var2, bbx var3) {
      this.oW().verifyLocked();
      this.Uv.put(var1, var3);
      return (bbx)this.RF((bbd)var3);
   }

   private bbd RF(bbd var1) {
      this.oW().verifyLocked();
      var1.Dw(this.HF);
      var1.q(-8791026472627208192L | this.HF & 4294967295L);
      this.LK.put(this.HF, var1);
      this.HF++;
      var1.addListener(this);
      return var1;
   }

   public bbd q(long var1) {
      if ((var1 & -72057594037927936L) != -8791026472627208192L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.q(var3);
      }
   }

   public bbd q(int var1) {
      return (bbd)this.LK.get(var1);
   }

   public bbm q(String var1) {
      bbm var6;
      try (ACLock var2 = this.oW().a()) {
         ArrayList var3 = new ArrayList();
         var1 = this.q(var1, var3);
         String var4 = (String)var3.get(var3.size() - 1);
         bbm var5 = new bbm(this, var4, var1);
         var6 = (bbm)this.q(var1, var3, var5);
      }

      return var6;
   }

   public bbv q(String var1, int var2, int var3) {
      bbv var8;
      try (ACLock var4 = this.oW().a()) {
         ArrayList var5 = new ArrayList();
         var1 = this.q(var1, var5);
         String var6 = (String)var5.get(var5.size() - 1);
         bbv var7 = new bbv(this, var6, var1, var2, var3);
         var8 = (bbv)this.q(var1, var5, var7);
      }

      return var8;
   }

   public bbv RF(String var1) {
      return this.q(var1, 1, 0);
   }

   public bbv q(String var1, String... var2) {
      if (var2.length % 2 != 0) {
         throw new IllegalArgumentException("Odd number of entries for name-and-types list");
      } else {
         bbv var3 = this.q(var1, 1, 0);

         for (byte var4 = 0; var4 < var2.length; var4 += 2) {
            String var5 = var2[var4];
            String var6 = var2[var4 + 1];
            if (this.addStructureField(var3, var5, var6) == null) {
               throw new IllegalArgumentException("Illegal field definition: " + var5 + " " + var6);
            }
         }

         return var3;
      }
   }

   public bbv xK(String var1) {
      return this.q(var1, 0, 0);
   }

   @Override
   public IStructureTypeField addStructureField(IStructureType var1, String var2, String var3) {
      INativeType var4 = TypeUtil.buildQuickType(this, var3);
      return var4 == null ? null : this.addStructureField(var1, var2, var4);
   }

   @Override
   public IStructureTypeField addStructureField(IStructureType var1, String var2, INativeType var3) {
      return this.addStructureField(var1, var2, var3, -1, 0, 0, 0);
   }

   @Override
   public IStructureTypeField addStructureField(IStructureType var1, String var2, INativeType var3, int var4, int var5, int var6, int var7) {
      bbu var9;
      try (ACLock var8 = this.oW().a()) {
         var9 = ((bbv)var1).q(var2, var3, var4, var5, var6, var7);
      }

      return var9;
   }

   @Override
   public boolean removeStructureField(IStructureType var1, IStructureTypeField var2) {
      boolean var4;
      try (ACLock var3 = this.oW().a()) {
         var4 = ((bbv)var1).q(var2);
      }

      return var4;
   }

   @Override
   public boolean renameStructureField(IStructureType var1, String var2, String var3) {
      boolean var6;
      try (ACLock var4 = this.oW().a()) {
         bbu var5 = ((bbv)var1).q(var2);
         if (var5 == null) {
            return false;
         }

         var6 = ((bbv)var1).q(var5, var3);
      }

      return var6;
   }

   @Override
   public boolean renameStructureField(IStructureType var1, IStructureTypeField var2, String var3) {
      boolean var5;
      try (ACLock var4 = this.oW().a()) {
         var5 = ((bbv)var1).q((bbu)var2, var3);
      }

      return var5;
   }

   public bbi Dw(String var1) {
      bbi var3;
      try (ACLock var2 = this.oW().a()) {
         var3 = this.RF(var1, 1, 1);
      }

      return var3;
   }

   public bbi q(String var1, int var2) {
      bbi var4;
      try (ACLock var3 = this.oW().a()) {
         var4 = this.RF(var1, var2, 1);
      }

      return var4;
   }

   public bbi RF(String var1, int var2, int var3) {
      bbi var8;
      try (ACLock var4 = this.oW().a()) {
         ArrayList var5 = new ArrayList();
         var1 = this.q(var1, var5);
         String var6 = (String)var5.get(var5.size() - 1);
         bbi var7 = new bbi(this, var6, var1, var2, var3);
         var8 = (bbi)this.q(var1, var5, var7);
      }

      return var8;
   }

   @Override
   public Couple createVirtualTableDefinition(String var1, Long var2, Collection var3) {
      bbz var4 = new bbz(this, var1);
      axx var5 = new axx(var2);

      for (INativeMethodItem var7 : var3) {
         var4.q(var7.getName(true));
         var5.q(var7);
      }

      var4.RF();
      var5.q();
      return new Couple(var4, var5);
   }

   @Override
   public void setClassVirtualTable(IClassType var1, IVirtualTableDefinition var2) {
      try (ACLock var3 = this.oW().a()) {
         ((bbi)var1).q(var2);
      }
   }

   @Override
   public void setClassSuperTypes(IClassType var1, Collection var2, Collection var3) {
      try (ACLock var4 = this.oW().a()) {
         ((bbi)var1).q(var2, var3);
      }
   }

   @Override
   public void completeClassTypeInitialization(IClassType var1) {
      ((bbi)var1).nf();
   }

   public bbe q(String var1, INativeType var2) {
      bbe var4;
      try (ACLock var3 = this.oW().a()) {
         if (var2 == null) {
            throw new NullPointerException();
         }

         var4 = this.RF(var1, var2);
      }

      return var4;
   }

   @Override
   public IAliasType createAlias(String var1, String var2) {
      bbe var5;
      try (ACLock var3 = this.oW().a()) {
         bbd var4 = this.Uv(var2);
         if (var4 == null) {
            throw new IllegalArgumentException("Unknown type: " + var2);
         }

         var5 = this.q(var1, var4);
      }

      return var5;
   }

   public bbe RF(String var1, INativeType var2) {
      bbe var7;
      try (ACLock var3 = this.oW().a()) {
         ArrayList var4 = new ArrayList();
         var1 = this.q(var1, var4, true);
         String var5 = (String)var4.get(var4.size() - 1);
         if (var2 == null) {
            return new bbe(this, var5, var1, null);
         }

         bbe var6 = new bbe(this, var5, var1, (bbd)var2);
         var7 = (bbe)this.q(var1, var4, var6);
      }

      return var7;
   }

   void q(bbe var1, bbd var2) {
      this.oW().verifyLocked();
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1.oW() != null) {
         throw new IllegalStateException();
      } else {
         var1.q(var2, false);
         ArrayList var3 = new ArrayList();
         String var4 = this.q(var1.getSignature(false), var3, true);
         this.q(var4, var3, var1);
      }
   }

   public bbd nf() {
      return this.Uv("void");
   }

   public bbt gP() {
      return this.Dz;
   }

   public bbt q(INativeType var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return this.q((bbd)var1, var2);
      }
   }

   @Override
   public IReferenceType createReference(INativeType var1) {
      return this.q(var1, 1);
   }

   public bbt q(bbd param1, int param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:359)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:504)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:178)
      //
      // Bytecode:
      // 00: aload 1
      // 01: ifnonnull 0f
      // 04: new com/pnfsoftware/jebglobal/bbt
      // 07: dup
      // 08: aload 0
      // 09: aconst_null
      // 0a: iload 2
      // 0b: invokespecial com/pnfsoftware/jebglobal/bbt.<init> (Lcom/pnfsoftware/jebglobal/bby;Lcom/pnfsoftware/jebglobal/bbd;I)V
      // 0e: areturn
      // 0f: aload 0
      // 10: getfield com/pnfsoftware/jebglobal/bby.gO Ljava/util/IdentityHashMap;
      // 13: dup
      // 14: astore 4
      // 16: monitorenter
      // 17: aload 0
      // 18: getfield com/pnfsoftware/jebglobal/bby.gO Ljava/util/IdentityHashMap;
      // 1b: aload 1
      // 1c: invokevirtual java/util/IdentityHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 1f: checkcast java/util/List
      // 22: astore 3
      // 23: aload 3
      // 24: ifnonnull 39
      // 27: new java/util/ArrayList
      // 2a: dup
      // 2b: invokespecial java/util/ArrayList.<init> ()V
      // 2e: astore 3
      // 2f: aload 0
      // 30: getfield com/pnfsoftware/jebglobal/bby.gO Ljava/util/IdentityHashMap;
      // 33: aload 1
      // 34: aload 3
      // 35: invokevirtual java/util/IdentityHashMap.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      // 38: pop
      // 39: aload 3
      // 3a: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 3f: astore 5
      // 41: aload 5
      // 43: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 48: ifeq 69
      // 4b: aload 5
      // 4d: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 52: checkcast com/pnfsoftware/jebglobal/bbt
      // 55: astore 6
      // 57: aload 6
      // 59: invokevirtual com/pnfsoftware/jebglobal/bbt.getReferenceCount ()I
      // 5c: iload 2
      // 5d: if_icmpne 66
      // 60: aload 6
      // 62: aload 4
      // 64: monitorexit
      // 65: areturn
      // 66: goto 41
      // 69: aload 4
      // 6b: monitorexit
      // 6c: goto 77
      // 6f: astore 7
      // 71: aload 4
      // 73: monitorexit
      // 74: aload 7
      // 76: athrow
      // 77: aload 0
      // 78: invokevirtual com/pnfsoftware/jebglobal/bby.oW ()Lcom/pnfsoftware/jeb/core/units/IUnitLock;
      // 7b: invokeinterface com/pnfsoftware/jeb/core/units/IUnitLock.a ()Lcom/pnfsoftware/jeb/util/concurrent/ACLock; 1
      // 80: astore 4
      // 82: new com/pnfsoftware/jebglobal/bbt
      // 85: dup
      // 86: aload 0
      // 87: aload 1
      // 88: iload 2
      // 89: invokespecial com/pnfsoftware/jebglobal/bbt.<init> (Lcom/pnfsoftware/jebglobal/bby;Lcom/pnfsoftware/jebglobal/bbd;I)V
      // 8c: astore 5
      // 8e: aload 3
      // 8f: aload 5
      // 91: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 96: pop
      // 97: aload 0
      // 98: aload 5
      // 9a: invokevirtual com/pnfsoftware/jebglobal/bby.RF (Lcom/pnfsoftware/jebglobal/bbd;)Lcom/pnfsoftware/jebglobal/bbd;
      // 9d: checkcast com/pnfsoftware/jebglobal/bbt
      // a0: astore 6
      // a2: aload 4
      // a4: ifnull ae
      // a7: aload 4
      // a9: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // ae: aload 6
      // b0: areturn
      // b1: astore 5
      // b3: aload 4
      // b5: ifnull cb
      // b8: aload 4
      // ba: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // bf: goto cb
      // c2: astore 6
      // c4: aload 5
      // c6: aload 6
      // c8: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // cb: aload 5
      // cd: athrow
   }

   void q(bbt var1, bbd var2) {
      this.oW().verifyLocked();
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1.oW() != null) {
         throw new IllegalStateException();
      } else {
         var1.q(var2, false);
         synchronized (this.gO) {
            Object var4 = (List)this.gO.get(var2);
            if (var4 == null) {
               var4 = new ArrayList();
               this.gO.put(var2, var4);
            }

            var4.add(var1);
         }

         this.RF((bbd)var1);
      }
   }

   @Override
   public IArrayType createArray(String var1, int var2) {
      INativeType var3 = TypeUtil.buildQuickType(this, var1);
      return var3 == null ? null : this.RF(var3, var2);
   }

   public bbf RF(INativeType var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return this.RF((bbd)var1, var2);
      }
   }

   public bbf q(INativeType var1, int var2, boolean var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var3) {
         bbf var6;
         try (ACLock var4 = this.oW().a()) {
            bbf var5 = new bbf(this, (bbd)var1, var2);
            var5.RF(128, false);
            var6 = (bbf)this.RF((bbd)var5);
         }

         return var6;
      } else {
         return this.RF((bbd)var1, var2);
      }
   }

   public bbf RF(bbd param1, int param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:359)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:504)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:178)
      //
      // Bytecode:
      // 00: aload 1
      // 01: ifnonnull 0f
      // 04: new com/pnfsoftware/jebglobal/bbf
      // 07: dup
      // 08: aload 0
      // 09: aconst_null
      // 0a: iload 2
      // 0b: invokespecial com/pnfsoftware/jebglobal/bbf.<init> (Lcom/pnfsoftware/jebglobal/bby;Lcom/pnfsoftware/jebglobal/bbd;I)V
      // 0e: areturn
      // 0f: aload 0
      // 10: getfield com/pnfsoftware/jebglobal/bby.oW Ljava/util/IdentityHashMap;
      // 13: dup
      // 14: astore 4
      // 16: monitorenter
      // 17: aload 0
      // 18: getfield com/pnfsoftware/jebglobal/bby.oW Ljava/util/IdentityHashMap;
      // 1b: aload 1
      // 1c: invokevirtual java/util/IdentityHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 1f: checkcast java/util/List
      // 22: astore 3
      // 23: aload 3
      // 24: ifnonnull 39
      // 27: new java/util/ArrayList
      // 2a: dup
      // 2b: invokespecial java/util/ArrayList.<init> ()V
      // 2e: astore 3
      // 2f: aload 0
      // 30: getfield com/pnfsoftware/jebglobal/bby.oW Ljava/util/IdentityHashMap;
      // 33: aload 1
      // 34: aload 3
      // 35: invokevirtual java/util/IdentityHashMap.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      // 38: pop
      // 39: aload 3
      // 3a: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 3f: astore 5
      // 41: aload 5
      // 43: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 48: ifeq 69
      // 4b: aload 5
      // 4d: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 52: checkcast com/pnfsoftware/jebglobal/bbf
      // 55: astore 6
      // 57: aload 6
      // 59: invokevirtual com/pnfsoftware/jebglobal/bbf.getElementCount ()I
      // 5c: iload 2
      // 5d: if_icmpne 66
      // 60: aload 6
      // 62: aload 4
      // 64: monitorexit
      // 65: areturn
      // 66: goto 41
      // 69: aload 4
      // 6b: monitorexit
      // 6c: goto 77
      // 6f: astore 7
      // 71: aload 4
      // 73: monitorexit
      // 74: aload 7
      // 76: athrow
      // 77: aload 0
      // 78: invokevirtual com/pnfsoftware/jebglobal/bby.oW ()Lcom/pnfsoftware/jeb/core/units/IUnitLock;
      // 7b: invokeinterface com/pnfsoftware/jeb/core/units/IUnitLock.a ()Lcom/pnfsoftware/jeb/util/concurrent/ACLock; 1
      // 80: astore 4
      // 82: new com/pnfsoftware/jebglobal/bbf
      // 85: dup
      // 86: aload 0
      // 87: aload 1
      // 88: iload 2
      // 89: invokespecial com/pnfsoftware/jebglobal/bbf.<init> (Lcom/pnfsoftware/jebglobal/bby;Lcom/pnfsoftware/jebglobal/bbd;I)V
      // 8c: astore 5
      // 8e: aload 3
      // 8f: aload 5
      // 91: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 96: pop
      // 97: aload 0
      // 98: aload 5
      // 9a: invokevirtual com/pnfsoftware/jebglobal/bby.RF (Lcom/pnfsoftware/jebglobal/bbd;)Lcom/pnfsoftware/jebglobal/bbd;
      // 9d: checkcast com/pnfsoftware/jebglobal/bbf
      // a0: astore 6
      // a2: aload 4
      // a4: ifnull ae
      // a7: aload 4
      // a9: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // ae: aload 6
      // b0: areturn
      // b1: astore 5
      // b3: aload 4
      // b5: ifnull cb
      // b8: aload 4
      // ba: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // bf: goto cb
      // c2: astore 6
      // c4: aload 5
      // c6: aload 6
      // c8: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // cb: aload 5
      // cd: athrow
   }

   void q(bbf var1, bbd var2) {
      this.oW().verifyLocked();
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1.oW() != null) {
         throw new IllegalStateException();
      } else {
         var1.q(var2, false);
         synchronized (this.oW) {
            Object var4 = (List)this.oW.get(var2);
            if (var4 == null) {
               var4 = new ArrayList();
               this.oW.put(var2, var4);
            }

            var4.add(var1);
         }

         this.RF((bbd)var1);
      }
   }

   public bbs q(ICallingConvention var1, List var2, List var3, Collection var4) {
      ArrayList var5 = new ArrayList();
      if (var2 != null) {
         for (INativeType var7 : var2) {
            var5.add((bbd)var7);
         }
      }

      ArrayList var9 = new ArrayList();
      if (var3 != null) {
         for (INativeType var8 : var3) {
            var9.add((bbd)var8);
         }
      }

      return this.q(var5, var9, var1, var4);
   }

   public bbs q(ICallingConvention var1, INativeType var2, List var3, Collection var4) {
      return this.q(var1, var2 == null ? null : Arrays.asList(var2), var3, var4);
   }

   public bbs q(INativeType var1, List var2) {
      return this.q(null, var1, var2, null);
   }

   public bbs za() {
      return this.q(null, null, null, null);
   }

   public bbs q(List var1, List var2, ICallingConvention var3, Collection var4) {
      bbs var7;
      try (ACLock var5 = this.oW().a()) {
         bbs var6 = new bbs(this, var3, var1, var2, var4);
         this.nf.add(var6);
         var7 = (bbs)this.RF((bbd)var6);
      }

      return var7;
   }

   public bbs q(String var1, List var2, List var3, Collection var4) {
      ICallingConvention var5 = null;
      if (var1 != null) {
         var5 = this.Dw().getConvention(var1);
      }

      ArrayList var6 = new ArrayList();
      if (var2 != null) {
         for (String var8 : var2) {
            var6.add((bbd)this.lm(var8));
         }
      }

      ArrayList var10 = new ArrayList();
      if (var3 != null) {
         for (String var9 : var3) {
            var10.add((bbd)this.lm(var9));
         }
      }

      return this.q(var6, var10, var5, var4);
   }

   private INativeType lm(String var1) {
      return TypeUtil.buildQuickType(this, var1);
   }

   public bbd Uv(String var1) {
      return this.q(var1, true, true);
   }

   public bbd q(String var1, boolean var2) {
      return this.q(var1, var2, true);
   }

   public bbd q(String var1, boolean var2, boolean var3) {
      var1 = TypeUtil.preNormalizeSignature(var1);
      bbq var4 = this.RF.q(var1);
      if (var4 != null) {
         return var4;
      } else {
         bbd var14 = (bbd)this.Uv.get(var1);
         if (var14 != null) {
            return var14;
         } else {
            ArrayList var5 = new ArrayList();
            int[] var6 = new int[2];
            var1 = TypeUtil.normalizeSignature(true, var1, var5, var6);
            if (var1 == null) {
               return null;
            } else {
               String var7 = TypeUtil.buildFullyQualifiedTypeNameFromElements(var5);
               Object var15 = this.RF.q(var7);
               if (var15 == null) {
                  var15 = (bbd)this.Uv.get(var7);
               }

               if (var15 != null) {
                  return this.q((bbd)var15, var6);
               } else {
                  if (var3) {
                     bbo var8 = this.lm;

                     for (int var9 = 0; var9 < var5.size() - 1; var9++) {
                        bbo var10 = var8.q((String)var5.get(var9), true);
                        if (var10 == null) {
                           var8 = null;
                           break;
                        }

                        var8 = var10;
                     }

                     if (var8 != null) {
                        String var20 = (String)var5.get(var5.size() - 1);

                        for (bbd var11 : var8.q(bbd.class)) {
                           if (Strings.equals(var20, var11.getName(true))) {
                              return this.q(var11, var6);
                           }
                        }
                     }
                  }

                  if (var2 && this.jq != null) {
                     var15 = (bbd)this.jq.findTypeBySignature(var1, this.JY);
                     if (var15 != null) {
                        bbd var19 = this.q((bbd)var15);
                        if (var19 == null) {
                           q.error("Import() failed for type: %s", var15);
                           return null;
                        }

                        return var19;
                     }

                     var15 = (bbd)this.jq.findTypeBySignature(var7, this.JY);
                     if (var15 != null) {
                        bbd var18 = this.q((bbd)var15);
                        if (var18 == null) {
                           q.error("Import() failed for type: %s", var15);
                           return null;
                        }

                        return this.q(var18, var6);
                     }
                  }

                  return "bool".equals(var7) ? this.RF.q("_Bool") : null;
               }
            }
         }
      }
   }

   private bbd q(bbd var1, int[] var2) {
      Object var3 = var1;
      if (var2[0] > 0) {
         var3 = this.q((INativeType)var1, var2[0]);
      }

      if (var2[1] >= 0) {
         var3 = this.RF((INativeType)var3, var2[1]);
      }

      return (bbd)var3;
   }

   public bbd oW(String var1) {
      bbd var2 = this.Uv(var1);
      if (var2 == null) {
         throw new JebRuntimeException(Strings.ff("Type not found: \"%s\"", var1));
      } else {
         return var2;
      }
   }

   public bbd q(int var1, boolean var2) {
      bbq var3 = this.RF.RF(var1, var2);
      return (bbd)TypeUtil.findShorterForm(var3);
   }

   public bbd RF(int var1, boolean var2) {
      bbq var3 = this.RF.q(var1, var2);
      return (bbd)TypeUtil.findShorterForm(var3);
   }

   public bbd RF(int var1) {
      bbq var2 = this.RF.q(var1);
      return (bbd)TypeUtil.findShorterForm(var2);
   }

   @Override
   public Collection getTypes() {
      return this.Uv.values();
   }

   @Override
   public Collection getTypes(ISimpleFilter var1) {
      ArrayList var2 = new ArrayList();

      for (INativeType var4 : this.Uv.values()) {
         if (var1 == null || var1.check(var4)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   private Iterator q(Class var1) {
      return new bby.eo(var1);
   }

   public Iterator lm() {
      return this.q(bbv.class);
   }

   public Iterator zz() {
      return this.q(bbm.class);
   }

   @Override
   public Collection getPrototypes() {
      return this.nf;
   }

   @Override
   public boolean deleteType(INativeType var1) {
      boolean var4;
      try (ACLock var2 = this.oW().a()) {
         bbd var3 = (bbd)var1;
         if (var3.isDisposed()) {
            return true;
         }

         if (var3 instanceof bbq) {
            q.warn("Attempt to dispose primitive type \"%s\" was denied", var1.getName(true));
            return false;
         }

         var3.PV();
         var4 = true;
      }

      return var4;
   }

   void q(INativeType var1) {
      this.oW().verifyLocked();
      if (var1 instanceof bbq) {
         throw new RuntimeException();
      } else {
         if (var1 instanceof bbf) {
            bbd var2 = ((bbf)var1).oW();
            Assert.a(var2 != null);
            synchronized (this.oW) {
               List var4 = (List)this.oW.get(var2);
               if (var4 != null) {
                  boolean var5 = var4.remove(var1);
                  Assert.a(var5, "Expected array type was not found: " + var1);
                  if (var4.isEmpty()) {
                     this.oW.remove(var2);
                  }
               }
            }
         } else if (var1 instanceof bbt) {
            bbd var12 = ((bbt)var1).oW();
            Assert.a(var12 != null);
            synchronized (this.gO) {
               List var17 = (List)this.gO.get(var12);
               if (var17 != null) {
                  boolean var19 = var17.remove(var1);
                  Assert.a(var19, "Expected reference type was not found: " + var1);
                  if (var17.isEmpty()) {
                     this.gO.remove(var12);
                  }
               }
            }
         } else if (var1 instanceof bbs) {
            boolean var13 = this.nf.remove(var1);
            Assert.a(var13, "Expected prototype was not found: " + var1);
         } else {
            String var14 = var1.getSignature(false);
            bbx var16 = (bbx)this.Uv.remove(var14);
            Assert.a(var16 == var1, "Expected true type was not found: " + var1);
            synchronized (this.za) {
               if (this.za.containsKey(var1)) {
                  boolean var20 = this.oW((bbd)var1);
                  Assert.a(var20, "Could not delete hierarchy node for type: " + var1);
               }
            }
         }

         this.LK.remove(var1.getIndex());
      }
   }

   @Override
   public String toString() {
      return Strings.ff("TYPEMAN(%d)", this.Uv.size());
   }

   public bbo JY() {
      return this.lm;
   }

   private bbo Uv(axj var1) {
      Assert.a(var1 != null);
      synchronized (this.za) {
         bbo var3 = (bbo)this.za.get(var1);
         if (var3 == null) {
            if (var1.isDisposed()) {
               throw new IllegalStateException("The item is disposed, refusing to create a node for it");
            }

            var3 = new bbo(var1);
            this.za.put(var1, var3);
         }

         return var3;
      }
   }

   public bbo q(axj var1) {
      return var1 == null ? this.lm : this.Uv(var1);
   }

   public axj RF(axj var1) {
      return this.q(var1).RF();
   }

   public axj xK(axj var1) {
      bbo var2 = this.q(var1).q();
      return var2 != null && var2 != this.lm ? var2.RF() : null;
   }

   bbo q(bbo var1, String var2) {
      bbo var6;
      try (ACLock var3 = this.oW().a()) {
         if (var1 == null && var2 != null) {
            var1 = this.lm;
         } else if (var1 == null && var2 != null) {
            throw new IllegalArgumentException("Illegal root node");
         }

         if (var1 != null) {
            bbo var4 = var1.q(var2);
            if (var4 != null) {
               return var4;
            }
         }

         bbp var9 = new bbp(this, var2);
         var9.q(-8286623314361712640L | this.sH & 4294967295L);
         this.CE.put(this.sH, var9);
         this.sH++;
         this.gP.add(var9);
         bbo var5 = this.Uv(var9);
         var5.q(var1);
         var5.q(true);
         var6 = var5;
      }

      return var6;
   }

   public bbo gO(String var1) {
      bbo var4;
      try (ACLock var2 = this.oW().a()) {
         List var3 = TypeUtil.splitCppName(var1);
         if (var3.isEmpty()) {
            return null;
         }

         var4 = this.q(var3);
      }

      return var4;
   }

   public bbo q(List var1) {
      bbo var9;
      try (ACLock var2 = this.oW().a()) {
         bbo var3 = this.lm;

         for (String var5 : var1) {
            bbo var6 = var3.q(var5);
            if (var6 == null) {
               var6 = this.q(var3, var5);
            }

            var3 = var6;
         }

         var9 = var3;
      }

      return var9;
   }

   @Override
   public List getPackages() {
      return Collections.unmodifiableList(this.gP);
   }

   public bbp nf(String var1) {
      ArrayList var2 = new ArrayList();
      if (!TypeUtil.isValidFullyQualifiedName(var1, var2, null)) {
         return null;
      } else {
         bbo var3 = this.lm;

         for (String var5 : var2) {
            bbo var6 = var3.q(var5, true);
            if (var6 == null) {
               return null;
            }

            var3 = var6;
         }

         return var3 == null ? null : (bbp)var3.RF();
      }
   }

   private bbx RF(bbo var1, String var2) {
      for (bbo var4 : var1.getChildren()) {
         axj var5 = var4.RF();
         if (var5 instanceof bbd) {
            String var6 = ((bbd)var5).getName(true);
            if (var6 != null && var2.startsWith(var6)) {
               if (var6.length() == var2.length()) {
                  return (bbx)var5;
               }

               bbx var7 = this.RF(var4, var2);
               if (var7 != null) {
                  return var7;
               }
            }
         }
      }

      return null;
   }

   public bbp Dw(axj var1) {
      bbo var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         for (bbo var3 = var2.q(); var3 != null; var3 = var3.q()) {
            axj var4 = var3.RF();
            if (var4 instanceof bbp) {
               return (bbp)var4;
            }
         }

         return null;
      }
   }

   public bbp q(INativeItem var1) {
      return var1 == null ? null : this.Dw((axj)var1);
   }

   public bbp q(IPackage var1, String var2) {
      bbp var5;
      try (ACLock var3 = this.oW().a()) {
         bbo var4 = this.q((axj)((bbp)var1));
         var4 = this.q(var4, var2);
         if (var4 == null) {
            return null;
         }

         var5 = (bbp)var4.RF();
      }

      return var5;
   }

   public bbp gP(String var1) {
      bbp var4;
      try (ACLock var2 = this.oW().a()) {
         bbo var3 = this.gO(var1);
         if (var3 == null) {
            return null;
         }

         var4 = (bbp)var3.RF();
      }

      return var4;
   }

   @Override
   public boolean moveToPackage(INativeItem var1, IPackage var2) {
      try (ACLock var3 = this.oW().a()) {
         bbp var4 = (bbp)var2;
         if (var1 instanceof axl var12) {
            this.q((axj)var12).q(null);
            this.q((axj)var4).RF(this.q((axj)var12));
            bbi var16 = var12.gO();
            this.q((axj)var16).q(null);
            this.q((axj)var4).RF(this.q((axj)var16));
            return true;
         }

         if (var1 instanceof axp var11) {
            if (var11.isVirtualMethod()) {
               q.error("The method is a virtual method of a class and cannot be moved");
               return false;
            }

            this.q((axj)var11).q(null);
            this.q((axj)var4).RF(this.q((axj)var11));
            return true;
         }

         if (var1 instanceof axm var10) {
            if (var10.gO()) {
               q.error("The field is an instance field of a class and cannot be moved");
               return false;
            }

            this.q((axj)var10).q(null);
            this.q((axj)var4).RF(this.q((axj)var10));
            return true;
         }
      }

      boolean var5;
      return var5;
   }

   @Override
   public boolean deletePackage(IPackage var1) {
      boolean var3;
      try (ACLock var2 = this.oW().a()) {
         var3 = this.oW((bbp)var1);
      }

      return var3;
   }

   public axp q(INativeItem var1, String var2, bbs var3, axo var4) {
      axp var9;
      try (ACLock var5 = this.oW().a()) {
         axp var6 = new axp(this, var2, var3, var4);
         var6.Dw(this.PV);
         var6.q(-8718968878589280256L | this.PV & 4294967295L);
         this.oQ.put(this.PV, var6);
         this.PV++;
         if (var1 != null) {
            this.q(var6, var1);
         }

         bbo var7 = this.Uv(var6);
         bbo var8 = var1 == null ? this.lm : this.Uv((axj)var1);
         var7.q(var8);
         var6.addListener(this);
         var9 = var6;
      }

      return var9;
   }

   public axp RF(long var1) {
      if ((var1 & -72057594037927936L) != -8718968878589280256L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.xK(var3);
      }
   }

   public axp xK(int var1) {
      return (axp)this.oQ.get(var1);
   }

   public Collection HF() {
      return this.oQ.values();
   }

   public bbp xK(long var1) {
      if ((var1 & -72057594037927936L) != -8286623314361712640L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.Dw(var3);
      }
   }

   public bbp Dw(int var1) {
      return (bbp)this.CE.get(var1);
   }

   public axm q(INativeItem var1, String var2, INativeType var3) {
      axm var6;
      try (ACLock var4 = this.oW().a()) {
         axm var5 = new axm(this, var2, (bbd)var3);
         var6 = this.q(var1, var5);
      }

      return var6;
   }

   public axm q(INativeItem var1, INativeDataItem var2) {
      axm var5;
      try (ACLock var3 = this.oW().a()) {
         axm var4 = new axm(this, var2);
         var5 = this.q(var1, var4);
      }

      return var5;
   }

   public axm q(INativeItem var1, bbv var2, bbu var3) {
      axm var6;
      try (ACLock var4 = this.oW().a()) {
         axm var5 = new axm(this, var2, var3);
         var6 = this.q(var1, var5);
      }

      return var6;
   }

   private axm q(INativeItem var1, axm var2) {
      this.oW().verifyLocked();
      var2.Dw(this.Hk);
      var2.q(-8358680908399640576L | this.Hk & 4294967295L);
      this.Me.put(this.Hk, var2);
      this.Hk++;
      if (var1 != null) {
         this.q(var2, var1);
      }

      bbo var3 = this.Uv(var2);
      bbo var4 = var1 == null ? this.lm : this.Uv((axj)var1);
      var3.q(var4);
      var2.addListener(this);
      return var2;
   }

   public axm Dw(long var1) {
      if ((var1 & -72057594037927936L) != -8358680908399640576L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.Uv(var3);
      }
   }

   public axm Uv(int var1) {
      return (axm)this.Me.get(var1);
   }

   public Collection LK() {
      return this.Me.values();
   }

   private axl q(IPackage var1, bbi var2) {
      this.oW().verifyLocked();
      axl var3 = new axl(this, var2.RF);
      var3.Dw(this.io);
      var3.q(-8430738502437568512L | this.io & 4294967295L);
      this.qa.put(this.io, var3);
      this.io++;
      if (var1 != null) {
         this.moveToPackage(var3, var1);
      }

      bbo var4 = this.Uv(var3);
      bbo var5 = var1 == null ? this.lm : this.Uv((bbp)var1);
      var4.q(var5);
      var3.addListener(this);
      return var3;
   }

   public axl q(IClassType var1) {
      return this.q((bbi)var1);
   }

   public axl q(bbi var1) {
      axl var4;
      try (ACLock var2 = this.oW().a()) {
         axl var3 = this.q(null, var1);
         var3.q(var1);
         var4 = var3;
      }

      return var4;
   }

   @Override
   public void setVirtualTableMethods(INativeClassItem var1, List var2) {
      try (ACLock var3 = this.oW().a()) {
         ((axl)var1).q(var2);
      }
   }

   @Override
   public void addNonVirtualMethod(INativeClassItem var1, INativeMethodItem var2) {
      try (ACLock var3 = this.oW().a()) {
         ((axl)var1).q((axp)var2);
         this.moveToClass(var2, var1);
      }
   }

   @Override
   public void addStaticMethod(INativeClassItem var1, INativeMethodItem var2) {
      try (ACLock var3 = this.oW().a()) {
         ((axl)var1).xK((axp)var2);
         this.moveToClass(var2, var1);
      }
   }

   @Override
   public void completeClassInitialization(INativeClassItem var1) {
      try (ACLock var2 = this.oW().a()) {
         ((axl)var1).Uv();
      }
   }

   public axl Uv(long var1) {
      if ((var1 & -72057594037927936L) != -8430738502437568512L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.oW(var3);
      }
   }

   public axl oW(int var1) {
      return (axl)this.qa.get(var1);
   }

   public Collection io() {
      return this.qa.values();
   }

   @Override
   public boolean moveToClass(INativeItem var1, INativeClassItem var2) {
      return this.q(var1, (axl)var2);
   }

   public boolean q(INativeItem var1, axl var2) {
      try (ACLock var3 = this.oW().a()) {
         if (var1 instanceof axl) {
            q.error("Inner classes are not supported");
            return false;
         }

         if (var1 instanceof axp var9) {
            if (var9.isVirtualMethod()) {
               q.error("The method is a virtual method of a class and cannot be moved");
               return false;
            }

            this.q((axj)var9).q(null);
            this.q((axj)var2).RF(this.q((axj)var9));
            return true;
         }

         if (var1 instanceof axm var8) {
            if (var8.gO()) {
               q.error("The field is an instance field of a class and cannot be moved");
               return false;
            }

            this.q((axj)var8).q(null);
            this.q((axj)var2).RF(this.q((axj)var8));
            return true;
         }
      }

      boolean var4;
      return var4;
   }

   private boolean oW(axj var1) {
      this.oW().verifyLocked();
      bbo var2 = this.q(var1);
      if (var2.hasChildren()) {
         return false;
      } else {
         this.q(var2);
         synchronized (this.za) {
            this.za.remove(var1);
            return true;
         }
      }
   }

   private void q(bbo var1) {
      this.oW().verifyLocked();
      bbo var2 = var1.q();
      if (var2 != null) {
         var2.xK(var1);
      }
   }

   public INativeItem za(String var1) {
      ArrayList var2 = new ArrayList();
      if (!TypeUtil.isValidFullyQualifiedName(var1, var2, null)) {
         return null;
      } else {
         bbo var3 = this.lm;

         for (String var5 : var2) {
            List var6 = var3.getChildren();
            var3 = this.q(var6, var5, false);
            if (var3 == null) {
               var3 = this.q(var6, var5, true);
               if (var3 == null) {
                  return null;
               }
            }
         }

         return var3.RF();
      }
   }

   private bbo q(List var1, String var2, boolean var3) {
      for (bbo var5 : var1) {
         axj var6 = var5.RF();
         if (!(var6 instanceof bbd) && Strings.equals(var6.getName(var3), var2)) {
            return var5;
         }
      }

      return null;
   }

   private boolean q(INativeItem var1, INativeItem var2) {
      try (ACLock var3 = this.oW().a()) {
         if (var2 instanceof IPackage) {
            return this.moveToPackage(var1, (IPackage)var2);
         }

         if (var2 instanceof axl) {
            return this.q(var1, (axl)var2);
         }
      }

      boolean var4;
      return var4;
   }

   public void q(axm var1) {
      this.oW().verifyLocked();
      this.Me.remove(var1.getIndex());
      this.oW(var1);
   }

   public void q(axp var1) {
      this.oW().verifyLocked();
      this.oQ.remove(var1.getIndex());
      this.oW(var1);
   }

   public void q(bbp var1) {
      this.oW().verifyLocked();
      this.CE.remove(var1.getIndex());
      this.oW(var1);
   }

   public void q(axl var1) {
      this.oW().verifyLocked();
      this.qa.remove(var1.getIndex());
      this.oW(var1);
   }

   public int qa() {
      return this.xW;
   }

   public bbd q(bbd var1) {
      bbd var5;
      try (ACLock var2 = this.oW().a()) {
         bbw var3 = new bbw(this);
         bbd var4 = var3.q(var1);
         this.xW = this.xW + var3.q();
         var5 = var4;
      }

      return var5;
   }

   public axp q(IMethodManager var1, axp var2) {
      if (var2.oW() != null) {
         throw new IllegalArgumentException("Cannot import internal routine");
      } else if (var2.xK() == this) {
         return var2;
      } else {
         axp var6;
         try (ACLock var3 = this.oW().a()) {
            bbs var4 = (bbs)this.q((bbd)var2.Uv());
            if (var4 == null) {
               q.warn("Failed to import: %s", var2);
               return null;
            }

            axp var5 = (axp)var1.createMethodReference(var2.getName(true), var4, null);
            var5.setParameterNames(new ArrayList(var2.getParameterNames()));
            var6 = var5;
         }

         return var6;
      }
   }

   @Override
   public void verify(INativeType var1) {
      ITypeManager var2 = var1.getTypeManager();
      if (var2 != this) {
         throw new JebRuntimeException("Illegal type, not managed by the current type manager");
      }
   }

   public bbd RF(String var1, boolean var2, boolean var3) {
      if (var1 == null) {
         return null;
      } else {
         int var4 = 0;
         boolean var5 = true;

         while (var5) {
            var5 = false;
            var1 = var1.trim();
            if (var1.endsWith("*")) {
               var4++;
               var1 = var1.substring(0, var1.length() - 1);
               var5 = true;
            }
         }

         Object var6 = this.Uv(var1);
         if (var6 == null) {
            if (var3) {
               var6 = this.Dw(var1);
            } else {
               try (ACLock var7 = this.oW().a()) {
                  ArrayList var9 = new ArrayList();
                  String var8 = this.q(var1, var9);
                  var6 = (bbd)this.mI.get(var8);
                  if (var6 == null) {
                     var6 = new bbi(this, var8, var8, 1, 1);
                     this.mI.put(var8, var6);
                  }
               }
            }
         }

         while (var4 > 0) {
            var6 = ((bbd)var6).Uv();
            var4--;
         }

         return (bbd)var6;
      }
   }

   public void Hk() {
      for (Entry var2 : this.qa.entrySet()) {
         ((axl)var2.getValue()).dispose(false);
      }

      for (Entry var6 : this.oQ.entrySet()) {
         ((axp)var6.getValue()).dispose(false);
      }

      for (int var5 = this.HF - 1; var5 >= 0; var5--) {
         try {
            bbd var7 = (bbd)this.LK.get(var5);
            if (var7 != null) {
               var7.dispose(false);
            }
         } catch (Exception var3) {
         }
      }

      this.RF = null;
      this.Dw = null;
      this.Uv = null;
      this.oW = null;
      this.gO = null;
      this.nf = null;
      this.gP = null;
      this.za = null;
      this.lm = null;
      this.zz = null;
      this.JY = null;
      this.LK = null;
      this.qa = null;
      this.Me = null;
      this.oQ = null;
      this.KT = null;
      this.Gf = null;
      this.Ef = null;
      this.CE = null;
      this.wF = null;
      this.If = null;
      this.Dz = null;
   }

   class eo implements Iterator {
      Class q;
      Iterator RF;
      INativeType xK;

      eo(Class var2) {
         this.q = var2;
         this.RF = bby.this.Uv.values().iterator();
         this.RF();
      }

      private void RF() {
         while (this.RF.hasNext()) {
            INativeType var1 = (INativeType)this.RF.next();
            var1 = TypeUtil.getNonAlias(var1);
            if (this.q.isInstance(var1)) {
               this.xK = var1;
               return;
            }
         }

         this.xK = null;
      }

      @Override
      public boolean hasNext() {
         return this.xK != null;
      }

      public INativeType q() {
         if (this.xK == null) {
            throw new NoSuchElementException();
         } else {
            INativeType var1 = this.xK;
            this.RF();
            return var1;
         }
      }
   }
}
