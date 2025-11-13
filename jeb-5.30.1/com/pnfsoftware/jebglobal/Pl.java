package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.BookmarkManager;
import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.IUserDataSupport;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.UserDataSupport;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.JebEventSource;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.ConfigurationMemoryMap;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class Pl extends JebEventSource implements IRuntimeProject {
   private static final ILogger gP = GlobalLog.getLogger(Pl.class);
   @SerTransient
   private long za;
   @SerTransient
   private zJ lm;
   @SerTransient
   private wq zz;
   @SerTransient
   private IPropertyDefinitionManager JY;
   @SerTransient
   private PropertyManager HF;
   @SerTransient
   private boolean LK;
   @SerTransient
   boolean q;
   @SerTransient
   public boolean RF;
   @SerTransient
   String xK;
   @SerId(1)
   private String io;
   @SerId(2)
   private String qa;
   @SerId(3)
   private String Hk;
   @SerId(4)
   private long Me;
   @SerId(5)
   private long PV;
   @SerId(6)
   private List oQ;
   @SerId(7)
   IConfiguration Dw;
   @SerId(8)
   private Map xW = new HashMap();
   @SerId(9)
   WeakIdentityHashMap Uv = new WeakIdentityHashMap();
   @SerId(10)
   List oW = new ArrayList();
   @SerId(11)
   private IUserDataSupport KT = new UserDataSupport();
   @SerId(12)
   int gO;
   @SerId(13)
   BookmarkManager nf = new BookmarkManager();

   @SerCustomInitPostGraph
   private void Dw() {
      if (this.KT == null) {
         this.KT = new UserDataSupport();
      }

      this.LK = true;
      this.Uv = new WeakIdentityHashMap();
      this.oW = new ArrayList();
      if (this.nf == null) {
         this.nf = new BookmarkManager();
      }

      this.addListener(this.nf);
   }

   Pl(String var1) {
      this.io = var1;
      this.qa = var1;
      this.Hk = "";
      this.Me = System.currentTimeMillis();
      this.PV = 0L;
      this.oQ = new ArrayList();
      this.nf = new BookmarkManager();
      this.addListener(this.nf);
   }

   @Override
   public void notifyListeners(JebEvent var1, boolean var2) {
      if (var1 != null && var1.getType() == J.UnitProcessed && var1.getSource() instanceof IUnit) {
         IUnit var3 = (IUnit)var1.getSource();
         if (var3.isProcessed()) {
            IQuickStateObject var4 = (IQuickStateObject)this.Uv.remove(var3);
            if (var4 == null && !this.oW.isEmpty()) {
               for (IQuickStateObject var6 : this.oW) {
                  if (var6.isTargetUnit(var3)) {
                     var4 = var6;
                     break;
                  }
               }

               if (var4 != null) {
                  this.oW.remove(var4);
               }
            }

            if (var4 != null) {
               if (var3 instanceof abg && !((abg)var3).isAnalysisCompleted()) {
                  ((abg)var3).Uv = var4;
               } else {
                  try {
                     var4.reload(var3);
                  } catch (Exception var7) {
                     gP.error(S.L("%s: Attempt to reload a quick-state failed"), UnitUtil.buildFullyQualifiedUnitPath(var3));
                  }
               }
            }
         }
      }

      super.notifyListeners(var1, var2);
   }

   void q(zJ var1, IPropertyDefinitionManager var2, boolean var3) {
      this.lm = var1;
      this.za = System.currentTimeMillis();
      if (this.io != null) {
         int var4 = Strings.lastIndexOf2(this.io, '/', '\\');
         if (var4 >= 0) {
            String var5 = this.io.substring(var4 + 1);
            if (!var5.isEmpty()) {
               this.io = var5;
            }
         }
      }

      if (this.qa != null) {
         int var12 = Strings.lastIndexOf2(this.qa, '/', '\\');
         if (var12 >= 0) {
            String var14 = this.qa.substring(var12 + 1);
            if (!var14.isEmpty()) {
               this.qa = var14;
            }
         }
      }

      this.JY = (IPropertyDefinitionManager)(var2 != null
         ? var2
         : new PropertyDefinitionManager(null, null, S.L("Project-specific properties. Modifications will not affect your global settings."), 0));
      PropertyDefinitionManager var13 = new PropertyDefinitionManager("project", this.JY);
      var13.addDefinition(
         "ArtifactProcessingDepth",
         PropertyTypeInteger.createPositive(20),
         S.L("Determine the maximum depth an input artifact (e.g., a file) will be explored to create units and sub-units representing analysis entities"),
         40
      );
      var13.addDefinition(
         "AlwaysProcessDuplicateInputs",
         PropertyTypeBoolean.create(false),
         S.L("Process binary inputs even if such input was seen earlier and processed as another unit already"),
         8
      );
      var13.addDefinition("CompressPersistedProject", PropertyTypeBoolean.create(true), S.L("Compress the JDB2 database (recommended)"), 8);
      var13.addDefinition(
         "PersistenceStrategy",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("Automatic"), S.L("Defaults to full-save unless quick-save is a viable alternative, which may be recommended to the user"))
            .addEntry(1, S.L("Full save"), S.L("Always full-save JDB2"))
            .addEntry(2, S.L("Quick save"), S.L("Always quick-save JDB2 (not recommended)"))
            .build(),
         S.L("Determine how a project will be saved to JDB2"),
         8
      );
      var13.addDefinition(
         "PersistArtifactFiles",
         PropertyTypeBoolean.create(true),
         S.L(
            "Persist the full artifact files in a JDB2 database. The resulting JDB2 file will be larger since the input files are embedded in it for extra safety."
         ),
         8
      );
      if (this.Dw == null) {
         IConfiguration var15 = var1.getPropertyManager().getConfiguration();
         if (var15 != null) {
            this.Dw = new ConfigurationMemoryMap(var15);
         } else {
            this.Dw = new ConfigurationMemoryMap();
         }
      }

      IPropertyManager var16 = var1.getPropertyManager();
      this.HF = new PropertyManager(this.JY, this.Dw, var16);
      this.zz = new wq(true, this.JY, this.HF);
      boolean var6 = this.HF.getBoolean(".project.AlwaysProcessDuplicateInputs");
      this.zz.q(var6);

      for (ry var8 : this.oQ) {
         IArtifact var9 = var8.getArtifact();
         if (this.xK != null && var9.getInput() instanceof FileInput) {
            FileInput var10 = (FileInput)var9.getInput();
            if (var10.getPersistedPath() != null && new File(var10.getPersistedPath()).isAbsolute()) {
               var10.setBase(this.xK);
            } else {
               var10.rebase(this.xK);
            }
         }

         var9.setParentSource(this);

         for (IUnit var11 : var8.getUnits()) {
            this.q(var11, var9, var3);
         }

         this.notifyListeners(new JebEvent(J.ArtifactProcessed, var8));
      }
   }

   private void q(IUnit var1, IUnitCreator var2, boolean var3) {
      var1.setUnitProcessor(this.zz);
      var1.setParent(var2);
      var1.setParentSource(var2);
      IPropertyDefinitionManager var4 = null;
      if (var1 instanceof cjl) {
         var4 = this.zz.xK();
      } else {
         IUnitIdentifier var5 = this.zz.getUnitIdentifier(var1.getFormatType());
         if (var5 != null) {
            var4 = var5.getPropertyDefinitionManager();
         } else {
            for (IUnitCreator var6 = var2; var6 instanceof IUnit; var6 = ((IUnit)var6).getParent()) {
               var4 = ((IUnit)var6).getPropertyDefinitionManager();
               if (var4 != null) {
                  break;
               }
            }
         }
      }

      var1.initializePropertyObjects(var2, this.zz, var4);
      if (var3) {
         var1.postDeserialization(this);
      }

      for (IUnit var8 : var1.getChildren()) {
         this.q(var8, var1, var3);
      }
   }

   boolean q() {
      try {
         return this.getPropertyManager().getBoolean(".project.CompressPersistedProject");
      } catch (Exception var1) {
         return false;
      }
   }

   @Override
   public int getPersistenceStrategy() {
      return this.getPropertyManager().getInteger(".project.PersistenceStrategy");
   }

   @Override
   public void setPersistenceStrategy(int var1) {
      this.getPropertyManager().setInteger(".project.PersistenceStrategy", var1);
   }

   public zJ RF() {
      return this.lm;
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.HF.getPropertyDefinitionManager();
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.HF;
   }

   @Override
   public String getKey() {
      return this.io;
   }

   @Override
   public String getPersistenceKey() {
      return this.RF().q(this);
   }

   @Override
   public boolean isReloaded() {
      return this.LK;
   }

   @Override
   public String getName() {
      return this.qa;
   }

   @Override
   public void setName(String var1) {
      if (var1 != null && !var1.equals(this.qa)) {
         this.qa = var1;
         this.notifyListeners(new JebEvent(J.ProjectPropertyChanged));
      }
   }

   @Override
   public String getNotes() {
      return this.Hk;
   }

   @Override
   public void setNotes(String var1) {
      if (var1 != null && !var1.equals(this.Hk)) {
         this.Hk = var1;
         this.notifyListeners(new JebEvent(J.ProjectPropertyChanged));
      }
   }

   @Override
   public IUnitProcessor getProcessor() {
      return this.zz;
   }

   @Override
   public long getCreationTimestamp() {
      return this.Me;
   }

   void xK() {
      this.PV = System.currentTimeMillis();
   }

   @Override
   public long getRecordedTimestamp() {
      return this.PV;
   }

   @Override
   public long getUptime() {
      return System.currentTimeMillis() - this.za;
   }

   @Override
   public void close() {
      Object[] var10000 = new Object[]{this.getName()};
      this.notifyListeners(new JebEvent(J.ProjectClosing, this));
      if (this.nf != null) {
         this.removeListener(this.nf);
      }

      for (ry var2 : this.oQ) {
         for (IUnit var4 : var2.getUnits()) {
            this.destroyUnit(var4);
         }
      }

      this.Me = 0L;
      this.io = null;
      this.lm = null;
      this.zz = null;
      this.oQ = null;
      this.JY = null;
      this.HF.dispose();
      this.HF = null;
   }

   @Override
   public boolean isClosed() {
      return this.oQ == null;
   }

   public ry q(IArtifact var1) {
      return this.q(var1, null, false, false, false, true);
   }

   public ry q(IArtifact var1, String var2, boolean var3, boolean var4) {
      return this.q(var1, var2, var3, var4, false, true);
   }

   ry q(IArtifact var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      if (!var5 && !this.lm.q().xK().Dw()) {
         throw new JebRuntimeException("Currently, your license does not permit the addition of artifacts to projects");
      } else {
         if (var1.getInput() instanceof FileInput) {
            FileInput var7 = (FileInput)var1.getInput();
            if (this.xK == null) {
               File var8 = var7.getFile();
               if (var8 != null) {
                  String var9 = var8.getParent();
                  this.xK = var9;
               }
            } else {
               var7.setBase(this.xK);
            }
         }

         var1.setParentSource(this);
         ry var10 = new ry(this, var1);
         this.oQ.add(var10);
         var10.load(var2, var3, var4);
         if (!var4 && var6) {
            this.notifyListeners(new JebEvent(J.ArtifactProcessed, var10));
         }

         return var10;
      }
   }

   void q(ry var1) {
      this.notifyListeners(new JebEvent(J.ArtifactProcessed, var1));
   }

   @Override
   public void finishArtifactProcessing(ILiveArtifact var1) {
      int var2 = 0;

      for (IUnit var4 : var1.getUnits()) {
         if (!var4.isProcessed()) {
            var4.process();
            var2++;
         }
      }

      if (var2 > 0) {
         this.notifyListeners(new JebEvent(J.ArtifactProcessed, var1));
      }
   }

   @Override
   public List getLiveArtifacts() {
      return (List)(this.oQ == null ? Collections.emptyList() : new ArrayList(this.oQ));
   }

   @Override
   public ILiveArtifact getLiveArtifact(int var1) {
      if (this.oQ == null) {
         return null;
      } else {
         return var1 >= 0 && var1 < this.oQ.size() ? (ILiveArtifact)this.oQ.get(var1) : null;
      }
   }

   @Override
   public int getArtifactCount() {
      return this.oQ == null ? 0 : this.oQ.size();
   }

   @Override
   public boolean destroyUnit(IUnit var1) {
      IUnitCreator var2 = var1.getParent();
      if (var2 instanceof IArtifact) {
         ry var3 = this.RF((IArtifact)var2);
         var3.q(var1);
      } else if (var2 instanceof IUnit) {
         ((IUnit)var2).removeChild(var1);
      }

      return true;
   }

   public boolean q(ILiveArtifact var1) {
      int var2 = this.oQ.indexOf(var1);
      if (var2 < 0) {
         return false;
      } else {
         for (IUnit var4 : var1.getUnits()) {
            boolean var5 = this.destroyUnit(var4);
            if (!var5) {
               gP.error(S.L("Cannot destroy unit %s"), var4);
               return false;
            }
         }

         this.oQ.remove(var2);
         this.notifyListeners(new JebEvent(J.ArtifactDestroyed, var1));
         return true;
      }
   }

   private ry RF(IArtifact var1) {
      for (ry var3 : this.oQ) {
         if (var3.getArtifact() == var1) {
            return var3;
         }
      }

      return null;
   }

   public List q(IInput param1, List param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 000: aload 0
      // 001: getfield com/pnfsoftware/jebglobal/Pl.xW Ljava/util/Map;
      // 004: ifnonnull 009
      // 007: aconst_null
      // 008: areturn
      // 009: aload 1
      // 00a: ifnonnull 00f
      // 00d: aconst_null
      // 00e: areturn
      // 00f: aload 1
      // 010: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getCurrentSize ()J 1
      // 015: lstore 3
      // 016: lload 3
      // 017: lconst_0
      // 018: lcmp
      // 019: ifge 01e
      // 01c: aconst_null
      // 01d: areturn
      // 01e: aload 0
      // 01f: getfield com/pnfsoftware/jebglobal/Pl.xW Ljava/util/Map;
      // 022: lload 3
      // 023: invokestatic java/lang/Long.valueOf (J)Ljava/lang/Long;
      // 026: invokeinterface java/util/Map.get (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 02b: checkcast java/util/List
      // 02e: astore 5
      // 030: aload 5
      // 032: ifnonnull 04e
      // 035: new java/util/ArrayList
      // 038: dup
      // 039: invokespecial java/util/ArrayList.<init> ()V
      // 03c: astore 5
      // 03e: aload 0
      // 03f: getfield com/pnfsoftware/jebglobal/Pl.xW Ljava/util/Map;
      // 042: lload 3
      // 043: invokestatic java/lang/Long.valueOf (J)Ljava/lang/Long;
      // 046: aload 5
      // 048: invokeinterface java/util/Map.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
      // 04d: pop
      // 04e: aload 1
      // 04f: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getHeader ()Ljava/nio/ByteBuffer; 1
      // 054: astore 6
      // 056: aload 6
      // 058: ifnull 065
      // 05b: aload 6
      // 05d: invokevirtual java/nio/ByteBuffer.remaining ()I
      // 060: bipush 8
      // 062: if_icmpge 067
      // 065: aconst_null
      // 066: areturn
      // 067: aload 6
      // 069: invokevirtual java/nio/ByteBuffer.getLong ()J
      // 06c: lstore 7
      // 06e: lload 3
      // 06f: ldc2_w 524288000
      // 072: lcmp
      // 073: ifle 07a
      // 076: bipush 1
      // 077: goto 07b
      // 07a: bipush 0
      // 07b: istore 9
      // 07d: iload 9
      // 07f: ifeq 09a
      // 082: getstatic com/pnfsoftware/jebglobal/Pl.gP Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 085: ldc "Calculating hash of large file (%s), be patient..."
      // 087: invokestatic com/pnfsoftware/jeb/client/S.L (Ljava/lang/String;)Ljava/lang/String;
      // 08a: bipush 1
      // 08b: anewarray 94
      // 08e: dup
      // 08f: bipush 0
      // 090: lload 3
      // 091: invokestatic com/pnfsoftware/jeb/util/format/SizeFormatter.formatByteSize (J)Ljava/lang/String;
      // 094: aastore
      // 095: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.warn (Ljava/lang/String;[Ljava/lang/Object;)V 3
      // 09a: aload 1
      // 09b: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getStream ()Ljava/io/InputStream; 1
      // 0a0: astore 11
      // 0a2: aload 11
      // 0a4: invokestatic com/pnfsoftware/jeb/util/encoding/HashCalculator.sha256 (Ljava/io/InputStream;)[B
      // 0a7: astore 10
      // 0a9: aload 10
      // 0ab: ifnonnull 0be
      // 0ae: aconst_null
      // 0af: astore 12
      // 0b1: aload 11
      // 0b3: ifnull 0bb
      // 0b6: aload 11
      // 0b8: invokevirtual java/io/InputStream.close ()V
      // 0bb: aload 12
      // 0bd: areturn
      // 0be: aload 11
      // 0c0: ifnull 0e6
      // 0c3: aload 11
      // 0c5: invokevirtual java/io/InputStream.close ()V
      // 0c8: goto 0e6
      // 0cb: astore 12
      // 0cd: aload 11
      // 0cf: ifnull 0e3
      // 0d2: aload 11
      // 0d4: invokevirtual java/io/InputStream.close ()V
      // 0d7: goto 0e3
      // 0da: astore 13
      // 0dc: aload 12
      // 0de: aload 13
      // 0e0: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // 0e3: aload 12
      // 0e5: athrow
      // 0e6: goto 0ec
      // 0e9: pop
      // 0ea: aconst_null
      // 0eb: areturn
      // 0ec: iload 9
      // 0ee: ifeq 10a
      // 0f1: getstatic com/pnfsoftware/jebglobal/Pl.gP Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 0f4: ldc "Hash calculation is complete (%s)"
      // 0f6: invokestatic com/pnfsoftware/jeb/client/S.L (Ljava/lang/String;)Ljava/lang/String;
      // 0f9: bipush 1
      // 0fa: anewarray 94
      // 0fd: dup
      // 0fe: bipush 0
      // 0ff: aload 10
      // 101: invokestatic com/pnfsoftware/jeb/util/format/Formatter.byteArrayToHex ([B)Ljava/lang/CharSequence;
      // 104: aastore
      // 105: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.info (Ljava/lang/String;[Ljava/lang/Object;)V 3
      // 10a: aload 5
      // 10c: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 111: astore 11
      // 113: aload 11
      // 115: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 11a: ifeq 15c
      // 11d: aload 11
      // 11f: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 124: checkcast com/pnfsoftware/jebglobal/Fw
      // 127: astore 12
      // 129: aload 12
      // 12b: getfield com/pnfsoftware/jebglobal/Fw.RF J
      // 12e: lload 7
      // 130: lcmp
      // 131: ifne 159
      // 134: aload 12
      // 136: getfield com/pnfsoftware/jebglobal/Fw.xK [B
      // 139: aload 10
      // 13b: invokestatic java/util/Arrays.equals ([B[B)Z
      // 13e: ifeq 159
      // 141: aload 12
      // 143: dup
      // 144: getfield com/pnfsoftware/jebglobal/Fw.Dw I
      // 147: bipush 1
      // 148: iadd
      // 149: putfield com/pnfsoftware/jebglobal/Fw.Dw I
      // 14c: new java/util/ArrayList
      // 14f: dup
      // 150: aload 12
      // 152: getfield com/pnfsoftware/jebglobal/Fw.Uv Ljava/util/List;
      // 155: invokespecial java/util/ArrayList.<init> (Ljava/util/Collection;)V
      // 158: areturn
      // 159: goto 113
      // 15c: aload 5
      // 15e: new com/pnfsoftware/jebglobal/Fw
      // 161: dup
      // 162: lload 3
      // 163: lload 7
      // 165: aload 10
      // 167: aload 2
      // 168: invokespecial com/pnfsoftware/jebglobal/Fw.<init> (JJ[BLjava/util/List;)V
      // 16b: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 170: pop
      // 171: aload 2
      // 172: areturn
      // 173: astore 3
      // 174: getstatic com/pnfsoftware/jebglobal/Pl.gP Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 177: aload 3
      // 178: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catchingSilent (Ljava/lang/Throwable;)V 2
      // 17d: aconst_null
      // 17e: areturn
   }

   @Override
   public List getInputRecords() {
      if (this.xW == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();

         for (List var3 : this.xW.values()) {
            var1.addAll(var3);
         }

         return var1;
      }
   }

   @Override
   public List findUnits(Class var1) {
      return RuntimeProjectUtil.findUnitsByType(this, var1, false);
   }

   @Override
   public IUnit findUnit(Class var1) {
      return RuntimeProjectUtil.findFirstUnitByType(this, var1, false);
   }

   @Override
   public void setData(Object var1, Object var2, boolean var3) {
      this.KT.setData(var1, var2, var3);
   }

   @Override
   public Object getData(Object var1) {
      return this.KT.getData(var1);
   }

   @Override
   public void clearAllData(Object var1) {
      this.KT.clearAllData(var1);
   }

   @Override
   public Map getAllData() {
      return this.KT.getAllData();
   }

   @Override
   public String toString() {
      return Strings.ff("Project:{%s}", this.getName());
   }

   @Override
   public BookmarkManager getBookmarkManager() {
      return this.nf;
   }
}
