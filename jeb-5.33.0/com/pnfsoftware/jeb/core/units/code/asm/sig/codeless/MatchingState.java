package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.KD;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.BiMap;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationException;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class MatchingState {
   static final ILogger logger = GlobalLog.getLogger(MatchingState.class);
   public static final String DEFAULT_NAME = "codeless-sig.state";
   @SerId(1)
   private ExecutableModelMetadata refMetadata;
   @SerId(2)
   private BiMap identifiedRoutines = new BiMap();
   @SerId(3)
   private Map routinesConstraints = new HashMap();
   @SerId(4)
   private Set nonIdentifiableRoutines = new HashSet();
   @SerId(5)
   Map possibleMultiIdentityRoutines = new HashMap();
   @SerId(6)
   Couple identifiedRoutinesRange;
   @SerId(7)
   private Map targetModules = new HashMap();
   @SerId(8)
   private SegmentMap targetModulesMapping = new SegmentMap();
   @SerTransient
   private String resultLog;

   public void addConstraint(Func var1, Constraint var2) {
      if (!this.getNonIdentifiableRoutines().contains(var1)) {
         Object var3 = (List)this.getRoutinesConstraints().get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.getRoutinesConstraints().put(var1, var3);
         }

         if (KD.A != null && var1.getName().equals(KD.A)) {
            Object[] var10000 = new Object[]{var2.getPossibleFuncs()};
         }

         var3.add(var2);
      }
   }

   public Module getModuleAt(long var1) {
      return (Module)this.getTargetModulesMapping().getSegmentContaining(var1);
   }

   public long getMatchedRangeStartAddress() {
      Module var1 = (Module)this.getTargetModulesMapping().firstEntry().getValue();
      return var1.getBegin();
   }

   public long getMatchedRangeEndAddress() {
      Module var1 = (Module)this.getTargetModulesMapping().lastEntry().getValue();
      return var1.getEnd();
   }

   public static MatchingState deserialize(String var0) {
      MatchingState var1 = null;

      try (FileInputStream var2 = new FileInputStream(new File(var0))) {
         ckh var3 = ckh.pC();
         SerializationManager var4 = new SerializationManager(var3);
         Deserializer var5 = var4.getDeserializer(var2);
         var1 = (MatchingState)var5.deserialize();
      } catch (IOException var8) {
      }

      return var1;
   }

   public static void serialize(MatchingState var0, String var1) throws IOException {
      ckh var2 = ckh.pC();
      SerializationManager var3 = new SerializationManager(var2);
      DirectByteArrayOutputStream var4 = new DirectByteArrayOutputStream();
      Serializer var5 = var3.getSerializer(var4);

      try {
         var5.serialize(var0);
         var5.close();
      } catch (SerializationException var8) {
         logger.catching(var8);
      }

      File var6 = new File(var1);
      if (!var6.exists()) {
         IO.createDirectory(var6);
      }

      File var7 = new File(var1.concat(File.separator).concat("codeless-sig.state"));
      IO.writeFile(var7, var4.getRawBytes(), 0, var4.size());
   }

   public Couple getIdentifiedRoutinesRange() {
      return this.identifiedRoutinesRange;
   }

   public void setIdentifiedRoutinesRange(long var1, long var3) {
      this.identifiedRoutinesRange = new Couple(var1, var3);
   }

   @Override
   public String toString() {
      return "MatchingState [identifiedRoutines="
         + this.getIdentifiedRoutines()
         + ", routinesConstraints="
         + this.getRoutinesConstraints()
         + ", nonIdentifiableRoutines="
         + this.getNonIdentifiableRoutines()
         + ", possibleMultiIdentityRoutines="
         + this.possibleMultiIdentityRoutines
         + ", identifiedRoutinesRange="
         + this.identifiedRoutinesRange
         + ", modulesSegmentMap="
         + this.getTargetModulesMapping()
         + ", modulesNamesMap="
         + this.getTargetModules()
         + "]";
   }

   public BiMap getIdentifiedRoutines() {
      return this.identifiedRoutines;
   }

   public void setIdentifiedRoutines(BiMap var1) {
      this.identifiedRoutines = var1;
   }

   public Map getRoutinesConstraints() {
      return this.routinesConstraints;
   }

   public void setRoutinesConstraints(Map var1) {
      this.routinesConstraints = var1;
   }

   public SegmentMap getTargetModulesMapping() {
      return this.targetModulesMapping;
   }

   public void setTargetModulesMapping(SegmentMap var1) {
      this.targetModulesMapping = var1;
   }

   public Map getTargetModules() {
      return this.targetModules;
   }

   public void setTargetModules(Map var1) {
      this.targetModules = var1;
   }

   public Set getNonIdentifiableRoutines() {
      return this.nonIdentifiableRoutines;
   }

   public void setNonIdentifiableRoutines(Set var1) {
      this.nonIdentifiableRoutines = var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.identifiedRoutines == null ? 0 : this.identifiedRoutines.size());
      var1 = 31 * var1 + (this.identifiedRoutinesRange == null ? 0 : this.identifiedRoutinesRange.hashCode());
      var1 = 31 * var1 + (this.nonIdentifiableRoutines == null ? 0 : this.nonIdentifiableRoutines.hashCode());
      var1 = 31 * var1 + (this.possibleMultiIdentityRoutines == null ? 0 : this.possibleMultiIdentityRoutines.hashCode());
      var1 = 31 * var1 + (this.routinesConstraints == null ? 0 : this.routinesConstraints.hashCode());
      var1 = 31 * var1 + (this.targetModules == null ? 0 : this.targetModules.hashCode());
      return 31 * var1 + (this.targetModulesMapping == null ? 0 : this.targetModulesMapping.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         MatchingState var2 = (MatchingState)var1;
         if (this.identifiedRoutines == null && var2.identifiedRoutines != null) {
            return false;
         } else {
            for (Func var4 : this.identifiedRoutines.keySet()) {
               if (!var2.identifiedRoutines.containsKey(var4) || !((Func)this.identifiedRoutines.get(var4)).equals(var2.identifiedRoutines.get(var4))) {
                  return false;
               }
            }

            for (Func var6 : var2.identifiedRoutines.keySet()) {
               if (!this.identifiedRoutines.containsKey(var6) || !((Func)this.identifiedRoutines.get(var6)).equals(var2.identifiedRoutines.get(var6))) {
                  return false;
               }
            }

            if (this.identifiedRoutinesRange == null) {
               if (var2.identifiedRoutinesRange != null) {
                  return false;
               }
            } else if (!this.identifiedRoutinesRange.equals(var2.identifiedRoutinesRange)) {
               return false;
            }

            if (this.nonIdentifiableRoutines == null) {
               if (var2.nonIdentifiableRoutines != null) {
                  return false;
               }
            } else if (!this.nonIdentifiableRoutines.equals(var2.nonIdentifiableRoutines)) {
               return false;
            }

            if (this.possibleMultiIdentityRoutines == null) {
               if (var2.possibleMultiIdentityRoutines != null) {
                  return false;
               }
            } else if (!this.possibleMultiIdentityRoutines.equals(var2.possibleMultiIdentityRoutines)) {
               return false;
            }

            if (this.routinesConstraints == null) {
               if (var2.routinesConstraints != null) {
                  return false;
               }
            } else if (!this.routinesConstraints.equals(var2.routinesConstraints)) {
               return false;
            }

            if (this.targetModules == null) {
               if (var2.targetModules != null) {
                  return false;
               }
            } else if (!this.targetModules.equals(var2.targetModules)) {
               return false;
            }

            if (this.targetModulesMapping == null) {
               if (var2.targetModulesMapping != null) {
                  return false;
               }
            } else if (!this.targetModulesMapping.equals(var2.targetModulesMapping)) {
               return false;
            }

            return true;
         }
      }
   }

   public ExecutableModelMetadata getRefMetadata() {
      return this.refMetadata;
   }

   public void setRefMetadata(ExecutableModelMetadata var1) {
      this.refMetadata = var1;
   }

   public String getResultLog() {
      return this.resultLog;
   }

   public void setResultLog(String var1) {
      this.resultLog = var1;
   }
}
