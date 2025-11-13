package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ArrayList1;
import com.pnfsoftware.jeb.util.collect.CacheMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFA4 implements IDFA {
   private static final ILogger logger = GlobalLog.getLogger(DFA4.class);
   public static final int CACHE_NONE = 0;
   public static final int CACHE_DUI = 1;
   public static final int CACHE_DUI_CHAINS = 2;
   public static final int CACHE_DUI_UDC_DUC = 2;
   private static final int CACHE_SIZE = 1000;
   private IControlFlowGraph cfg;
   private int cachecfg;
   private Map duimap;
   private CacheMap udCache;
   private CacheMap duCache;
   private CacheMap vrCache;
   private int varCollectionFlags;
   private boolean integrateCalculatedInputRegisters;
   private ICFGOwnerContext varInfoProvider;
   private final boolean recordIrrFlows = true;
   private final boolean defaultRecordLiveIn = true;
   private final boolean defaultRecordReachOut = false;
   private boolean alwaysExamineIrregularFlows = false;
   private int defaultMaxBlocks = -1;

   public DFA4(IControlFlowGraph var1) {
      this(var1, 3, 1);
   }

   public DFA4(IControlFlowGraph var1, int var2, int var3) {
      this.cfg = var1;
      this.varCollectionFlags = var2;
      this.cachecfg = var3;
   }

   @Override
   public void perform() {
   }

   @Override
   public boolean isValid() {
      return true;
   }

   @Override
   public void invalidate() {
      this.duimap = null;
      this.resetChainCaches();
   }

   @Override
   public void notifyInstructionUpdate(long var1) {
      if (this.duimap != null) {
         this.duimap.remove(var1);
      }

      this.resetChainCaches();
   }

   public void resetChainCaches() {
      if (this.duCache != null) {
         this.duCache.clear();
      }

      if (this.udCache != null) {
         this.udCache.clear();
      }

      if (this.vrCache != null) {
         this.vrCache.clear();
      }
   }

   @Override
   public void invalidateForRemoval(long var1) {
      this.invalidateForSubstitution(var1, Collections.emptyList(), null);
   }

   @Override
   public void invalidateForSubstitution(long var1, Collection var3, Collection var4) {
      if (this.duimap != null && this.udCache != null && this.duCache != null) {
         if (this.cachecfg >= 2) {
            DUI var5 = this.getDUI(var1);
            Collection var6 = var4 != null ? null : var5.getUse();

            for (DFA4.CacheKey var8 : this.duCache.keys()) {
               if ((var4 == null || !var4.contains(var8.insnaddr)) && (var6 == null || !var6.contains(var8.varid))) {
                  if (var8.insnaddr == var1) {
                     this.duCache.remove(var8);
                  } else if (var8.insnaddr == -1L) {
                     this.duCache.remove(var8);
                  }
               } else {
                  this.duCache.remove(var8);
               }
            }

            for (DFA4.CacheKey var12 : this.udCache.keys()) {
               if (var3.contains(var12.insnaddr)) {
                  this.udCache.remove(var12);
               } else if (var12.insnaddr == var1) {
                  this.udCache.remove(var12);
               } else if (var12.insnaddr == -1L) {
                  this.udCache.remove(var12);
               }
            }

            if (this.vrCache != null) {
               Collection var13 = var5.getDef();

               for (DFA4.VRCacheKey var10 : this.vrCache.keys()) {
                  if (var13.contains(var10.varid)) {
                     this.vrCache.remove(var10);
                  }
               }
            }
         }

         if (this.cachecfg >= 1) {
            this.duimap.remove(var1);
            var3.forEach(var1x -> this.duimap.remove(var1x));
         }
      } else {
         this.invalidate();
      }
   }

   @Override
   public void invalidatePostSimpleSubstitutionWithMultiDefs(Collection var1, long var2, int var4) {
      if (this.duimap != null && this.udCache != null && this.duCache != null) {
         if (this.cachecfg >= 2) {
            for (DFA4.CacheKey var6 : this.duCache.keys()) {
               if (var1.contains(var6.insnaddr)) {
                  this.duCache.remove(var6);
               }
            }

            for (DFA4.CacheKey var8 : this.udCache.keys()) {
               if (var8.insnaddr == var2) {
                  this.udCache.remove(var8);
               } else if (var8.insnaddr == -1L && var8.varid == var4) {
                  this.udCache.remove(var8);
               }
            }
         }

         if (this.cachecfg >= 1) {
            this.duimap.remove(var2);
         }
      } else {
         this.invalidate();
      }
   }

   @Override
   public IControlFlowGraph getCfg() {
      return this.cfg;
   }

   @Override
   public void setVariableCollectionFlags(int var1) {
      if (var1 != this.varCollectionFlags) {
         this.invalidate();
         this.varCollectionFlags = var1;
      }
   }

   @Override
   public int getVariableCollectionFlags() {
      return this.varCollectionFlags;
   }

   public void setCacheConfiguration(int var1) {
      if (var1 != this.cachecfg) {
         switch (var1) {
            case 0:
               this.duimap = null;
            case 1:
               this.resetChainCaches();
            case 2:
               this.cachecfg = var1;
               return;
            default:
               throw new IllegalArgumentException("Illegal cache configuration value: " + var1);
         }
      }
   }

   public int getCacheConfiguration() {
      return this.cachecfg;
   }

   @Override
   public boolean setIntegrateCalculatedInputRegisters(boolean var1) {
      boolean var2 = this.integrateCalculatedInputRegisters;
      if (var1 != this.integrateCalculatedInputRegisters) {
         this.invalidate();
         this.integrateCalculatedInputRegisters = var1;
      }

      return var2;
   }

   @Override
   public boolean isIntegrateCalculatedInputRegisters() {
      return this.integrateCalculatedInputRegisters;
   }

   @Override
   public boolean setAlwaysExamineIrregularFlows(boolean var1) {
      boolean var2 = this.alwaysExamineIrregularFlows;
      if (var1 != this.alwaysExamineIrregularFlows) {
         this.invalidate();
         this.alwaysExamineIrregularFlows = var1;
      }

      return var2;
   }

   @Override
   public boolean isAlwaysExamineIrregularFlows() {
      return this.alwaysExamineIrregularFlows;
   }

   @Override
   public ICFGOwnerContext setVariableInformationProvider(ICFGOwnerContext var1) {
      ICFGOwnerContext var2 = this.varInfoProvider;
      if (var1 != this.varInfoProvider) {
         this.invalidate();
         this.varInfoProvider = var1;
      }

      return var2;
   }

   @Override
   public ICFGOwnerContext getVariableInformationProvider() {
      return this.varInfoProvider;
   }

   @Override
   public int setMaxBlocks(int var1) {
      int var2 = this.defaultMaxBlocks;
      if (var1 < 0) {
         var1 = -1;
      }

      if (this.defaultMaxBlocks != var1) {
         this.defaultMaxBlocks = var1;
         this.resetChainCaches();
      }

      return var2;
   }

   @Override
   public int getMaxBlocks() {
      return this.defaultMaxBlocks;
   }

   public String formatDUI(DUI var1) {
      StringBuilder var2 = new StringBuilder();
      this.formatVars("uses", var1.getUse(), var2);
      this.formatVars("uses-maybe", var1.getUsePot(), var2);
      this.formatVars("defs", var1.getDef(), var2);
      this.formatVars("defs-maybe", var1.getDefPot(), var2);
      this.formatVars("spoiled", var1.getSpoiled(), var2);
      return var2.toString();
   }

   private void formatVars(String var1, Collection var2, StringBuilder var3) {
      var3.append(var1).append(": ");

      for (int var5 : var2) {
         if (this.varInfoProvider == null) {
            Strings.ff("r%X", var5);
         } else {
            var3.append(this.varInfoProvider.getName(var5));
         }

         var3.append(' ');
      }

      var3.append('\n');
   }

   @Override
   public DUI getDUI(long var1) {
      return this.getDUI(var1, null);
   }

   @Override
   public DUI getDUI(AddressableInstruction var1) {
      return this.getDUI(var1.getOffset(), var1.getInstruction());
   }

   @Override
   public DUI getDUI(long var1, IInstruction var3) {
      if (this.cachecfg >= 1) {
         if (this.duimap == null) {
            this.duimap = new HashMap();
         }

         DUI var4 = (DUI)this.duimap.get(var1);
         if (var4 != null) {
            return var4;
         }
      }

      if (var3 == null) {
         var3 = this.cfg.getInstruction(var1);
      }

      List var6 = Collections.emptyList();
      List var7 = Collections.emptyList();
      List var8 = Collections.emptyList();

      Object var5;
      Object var11;
      try {
         DefUseInfo var9 = var3.getDefUseInfo(var1, this.varCollectionFlags);
         var11 = var9.def.getVarIds();
         var5 = var9.use.getVarIds();
         if (var9.defpot != null) {
            var6 = var9.defpot.getVarIds();
            var6.removeAll((Collection)var11);
         }

         if (var9.usepot != null) {
            var7 = var9.usepot.getVarIds();
            var7.removeAll((Collection)var5);
         }

         if (var9.spoiled != null) {
            var8 = var9.spoiled.getVarIds();
            var8.removeAll((Collection)var11);
            var8.removeAll(var6);
         }
      } catch (Exception var10) {
         if (Licensing.isDebugBuild()) {
            throw new RuntimeException("getDefUse() failed!", var10);
         }

         var11 = new ArrayList();
         var5 = new ArrayList();
         var3.getDefUse((List)var11, (List)var5);
      }

      DUI var12 = new DUI((List)var11, (List)var5, var6, var7, var8);
      if (this.duimap != null) {
         this.duimap.put(var1, var12);
      }

      return var12;
   }

   public Collection getUseDefs(
      long var1, int var3, int var4, boolean var5, boolean var6, IBasicBlock var7, int var8, boolean[] var9, boolean var10, int var11, boolean var12
   ) {
      DFA4.CacheKey var13 = null;
      Collection var14 = null;
      if (this.cachecfg >= 2) {
         if (var7 == null) {
            if (var5 && var6 && var11 == this.defaultMaxBlocks && !var12) {
               var13 = new DFA4.CacheKey(var1, var3);
            }
         } else if (!var5 && var6 && var11 == this.defaultMaxBlocks && !var12) {
            var13 = new DFA4.CacheKey(var7.getBase(), var8, var3);
         }

         if (var13 != null) {
            if (this.udCache == null) {
               this.udCache = new CacheMap(1000);
            } else {
               var14 = (Collection)this.udCache.get(var13);
            }
         }
      }

      if (var14 == null) {
         var14 = this.getUseDefs_INTERNAL(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
         if (var13 != null && var14 != null && var4 <= 0) {
            this.udCache.put(var13, var14);
         }
      }

      return var14;
   }

   private Collection getUseDefs_INTERNAL(
      long var1, int var3, int var4, boolean var5, boolean var6, IBasicBlock var7, int var8, boolean[] var9, boolean var10, int var11, boolean var12
   ) {
      IBasicBlock var13;
      int var14;
      if (var7 != null) {
         Assert.a(!var5);
         Assert.a(!var12);
         if (var8 < 0 || var8 > var7.size()) {
            throw new IllegalArgumentException("Invalid instruction index for block: " + var8);
         }

         var13 = var7;
         var14 = var8;
      } else {
         Couple var16 = this.cfg.getInstructionLocation(var1);
         if (var16 == null) {
            throw new IllegalArgumentException(Strings.ff("Address does not refer to an instruction: %X", var1));
         }

         var13 = (IBasicBlock)var16.getFirst();
         var14 = (Integer)var16.getSecond();
         if (var5) {
            IInstruction var17 = var13.get(var14);
            DUI var15 = this.getDUI(var1, var17);
            if (!var15.use.contains(var3) && !var15.usepot.contains(var3)) {
               return null;
            }
         }
      }

      if (var4 <= 0) {
         var4 = Integer.MAX_VALUE;
      }

      ArrayDeque var35 = new ArrayDeque();
      HashSet var36 = new HashSet();
      ArrayDeque var18 = new ArrayDeque();
      HashSet var19 = new HashSet();
      int var20 = 0;
      LinkedHashSet var21 = new LinkedHashSet();
      boolean var22 = false;
      IBasicBlock var23 = this.cfg.getEntryBlock();

      label201:
      while (true) {
         IBasicBlock var24;
         int var25;
         int var26;
         boolean var27;
         if (var20++ == 0) {
            var24 = var13;
            var25 = var14 - 1;
            var26 = 0;
            var27 = false;
         } else if (!var35.isEmpty()) {
            var24 = (IBasicBlock)var35.remove();
            if (!var36.add(var24.getBase())) {
               continue;
            }

            var25 = var24.size() - 1;
            if (var24 == var13) {
               var26 = var14;
            } else {
               var26 = 0;
            }

            var27 = false;
         } else {
            if (var18 == null || var18.isEmpty()) {
               break;
            }

            var24 = (IBasicBlock)var18.remove();
            if (!var19.add(var24.getBase())) {
               continue;
            }

            var25 = var24.size() - 1;
            if (var24 == var13) {
               var26 = var14;
            } else {
               var26 = 0;
            }

            var27 = true;
         }

         if (var11-- == 0) {
            var21.add(-2L);
            break;
         }

         if (var25 >= var26) {
            boolean var28 = false;
            boolean[] var29 = new boolean[var25 + 1];

            for (int var30 = var25; var30 >= var26; var30--) {
               boolean var31 = var24.get(var30).canThrow();
               if (var31) {
                  var28 = true;
                  var29[var30] = true;
               }
            }

            boolean var37 = false;
            if (!var28 && this.alwaysExamineIrregularFlows) {
               var37 = true;
            }

            var1 = var24.getAddressOfInstruction(var25) + var24.get(var25).getSize();

            for (int var38 = var25; var38 >= var26; var38--) {
               IInstruction var32 = var24.get(var38);
               var1 -= var32.getSize();
               DUI var34 = this.getDUI(var1, var32);
               if (!var27) {
                  if (var34.def.contains(var3)) {
                     if (var12 && var24 != var13) {
                        var21.add(-2L);
                        return Collections.unmodifiableSet(var21);
                     }

                     var21.add(var1);
                     if (var21.size() >= var4) {
                        return Collections.unmodifiableSet(var21);
                     }
                     continue label201;
                  }

                  if (var34.defpot.contains(var3)) {
                     if (var12 && var24 != var13) {
                        var21.add(-2L);
                        return Collections.unmodifiableSet(var21);
                     }

                     var21.add(var1);
                     if (var21.size() >= var4) {
                        return Collections.unmodifiableSet(var21);
                     }
                  }

                  if (var34.spoiled.contains(var3)) {
                     if (var9 != null) {
                        var9[0] = true;
                        return Collections.unmodifiableSet(var21);
                     }

                     if (var10) {
                        if (var12 && var24 != var13) {
                           var21.add(-2L);
                           return Collections.unmodifiableSet(var21);
                        }

                        var21.add(var1);
                     }

                     if (var21.size() >= var4) {
                        return Collections.unmodifiableSet(var21);
                     }
                     continue label201;
                  }
               } else if (var37) {
                  if (var34.def.contains(var3) || var34.defpot.contains(var3) || var10 && var34.spoiled.contains(var3)) {
                     if (var12 && var24 != var13) {
                        var21.add(-2L);
                        return Collections.unmodifiableSet(var21);
                     }

                     var21.add(var1);
                     if (var21.size() >= var4) {
                        return Collections.unmodifiableSet(var21);
                     }

                     var37 = var29[var38];
                  }
               } else if (var29[var38]) {
                  var37 = true;
               }
            }

            if (var27 && !var37) {
               continue;
            }
         }

         if ((var24.insize() == 0 && var24.irrinsize() == 0 || var24 == var23) && var6 && !var22) {
            var22 = true;
            var21.add(-1L);
            if (var21.size() >= var4) {
               break;
            }
         }

         var35.addAll(var24.getInputs());
         var18.addAll(var24.getIrregularInputs());
      }

      return Collections.unmodifiableSet(var21);
   }

   @Override
   public Collection getUseDefs(long var1, int var3) {
      return this.getUseDefs(var1, var3, -1, true, true, null, -1, null, false, this.defaultMaxBlocks, false);
   }

   @Override
   public Collection getUseDefs(long var1, int var3, int var4) {
      return this.getUseDefs(var1, var3, var4, true, true, null, -1, null, false, this.defaultMaxBlocks, false);
   }

   @Override
   public Collection getBlockUseDefs(long var1, int var3) {
      return this.getUseDefs(var1, var3, -1, true, false, null, -1, null, false, -1, true);
   }

   @Override
   public boolean isTerminator(IBasicBlock var1) {
      return var1.outsize() == 0 && var1.getLast().getInstructionFlags().contains(InstructionFlags.ROUTINE_TERMINATOR);
   }

   public Collection getDefUses(long var1, int var3, int var4, boolean var5, boolean var6, IBasicBlock var7, int var8, int var9, boolean var10) {
      DFA4.CacheKey var11 = null;
      Collection var12 = null;
      if (this.cachecfg >= 2) {
         if (var7 == null) {
            if (var5 && !var6 && var9 == this.defaultMaxBlocks && !var10) {
               var11 = new DFA4.CacheKey(var1, var3);
            }
         } else if (!var5 && !var6 && var9 == this.defaultMaxBlocks && !var10) {
            var11 = new DFA4.CacheKey(var7.getBase(), var8, var3);
         }

         if (var11 != null) {
            if (this.duCache == null) {
               this.duCache = new CacheMap(1000);
            } else {
               var12 = (Collection)this.duCache.get(var11);
            }
         }
      }

      if (var12 == null) {
         var12 = this.getDefUses_INTERNAL(var1, var3, var4, var5, var6, var7, var8, var9, var10);
         if (var11 != null && var12 != null && var4 <= 0) {
            this.duCache.put(var11, var12);
         }
      }

      return var12;
   }

   private Collection getDefUses_INTERNAL(long var1, int var3, int var4, boolean var5, boolean var6, IBasicBlock var7, int var8, int var9, boolean var10) {
      IBasicBlock var11;
      int var12;
      if (var7 != null) {
         Assert.a(!var5);
         Assert.a(!var10);
         if (var8 < 0 || var8 > var7.size()) {
            throw new IllegalArgumentException("Invalid instruction index for block: " + var8);
         }

         var11 = var7;
         var12 = var8 - 1;
      } else {
         Couple var14 = this.cfg.getInstructionLocation(var1);
         if (var14 == null) {
            throw new IllegalArgumentException(Strings.ff("Address does not refer to an instruction: %X", var1));
         }

         var11 = (IBasicBlock)var14.getFirst();
         var12 = (Integer)var14.getSecond();
         if (var5) {
            IInstruction var15 = var11.get(var12);
            DUI var13 = this.getDUI(var1, var15);
            if (!var13.def.contains(var3) && !var13.defpot.contains(var3)) {
               return null;
            }
         }
      }

      if (var4 <= 0) {
         var4 = Integer.MAX_VALUE;
      }

      ArrayDeque var30 = new ArrayDeque();
      HashSet var31 = new HashSet();
      int var16 = 0;
      LinkedHashSet var17 = new LinkedHashSet();
      boolean var18 = false;

      label152:
      while (var16 == 0 || !var30.isEmpty()) {
         IBasicBlock var19;
         int var20;
         int var21;
         if (var16++ == 0) {
            var19 = var11;
            var20 = var12 + 1;
            var21 = var11.size() - 1;
         } else {
            var19 = (IBasicBlock)var30.remove();
            if (!var31.add(var19.getBase())) {
               continue;
            }

            var20 = 0;
            if (var19 == var11) {
               var21 = var12;
            } else {
               var21 = var19.size() - 1;
            }
         }

         if (var9-- == 0) {
            var17.add(-2L);
            break;
         }

         boolean var22 = false;
         boolean var23 = false;
         if (var20 <= var21) {
            boolean var24 = false;
            boolean[] var25 = new boolean[var21 + 1];

            for (int var26 = var20; var26 <= var21; var26++) {
               boolean var27 = var19.get(var26).canThrow();
               if (var27) {
                  var24 = true;
                  var25[var26] = true;
               }
            }

            if (!var24 && this.alwaysExamineIrregularFlows) {
               var23 = true;
            }

            var1 = var19.getAddressOfInstruction(var20);

            for (int var32 = var20; var32 <= var21; var32++) {
               IInstruction var33 = var19.get(var32);
               DUI var29 = this.getDUI(var1, var33);
               if (var29.use.contains(var3) || var29.usepot.contains(var3)) {
                  if (var10 && var19 != var11) {
                     var17.add(-2L);
                     return Collections.unmodifiableSet(var17);
                  }

                  var17.add(var1);
                  if (var17.size() >= var4) {
                     return Collections.unmodifiableSet(var17);
                  }
               }

               if (!var23 && var25[var32]) {
                  var23 = true;
               }

               if (var29.def.contains(var3) || var29.spoiled.contains(var3)) {
                  if (!var23 || var19.irroutsize() == 0) {
                     continue label152;
                  }

                  var22 = true;
                  break;
               }

               var1 += var33.getSize();
            }
         }

         if (!var22 && (var16 == 1 || var19 != var11)) {
            if (var19.outsize() == 0) {
               if (var6 && !this.isTerminator(var19) && !var18) {
                  var18 = true;
                  var17.add(-1L);
                  if (var17.size() >= var4) {
                     break;
                  }
               }
            } else {
               var30.addAll(var19.getOutputs());
            }
         }

         if (var23) {
            var30.addAll(var19.getIrregularOutputs());
         }
      }

      return Collections.unmodifiableSet(var17);
   }

   @Override
   public Collection getDefUses(long var1, int var3) {
      return this.getDefUses(var1, var3, -1, true, false, null, -1, this.defaultMaxBlocks, false);
   }

   @Override
   public Collection getDefUses(long var1, int var3, int var4) {
      return this.getDefUses(var1, var3, var4, true, false, null, -1, this.defaultMaxBlocks, false);
   }

   @Override
   public Collection getDefUses(long var1, int var3, int var4, boolean var5) {
      return this.getDefUses(var1, var3, var4, true, var5, null, -1, this.defaultMaxBlocks, false);
   }

   @Override
   public Collection getBlockDefUses(long var1, int var3) {
      return this.getDefUses(var1, var3, -1, true, false, null, -1, -1, true);
   }

   @Override
   public int checkNoUse(long var1, int var3, boolean var4, int var5) {
      Collection var6 = this.getDefUses(var1, var3, 1, true, var4, null, -1, var5, false);
      if (var6 == null) {
         return -3;
      } else if (var6.isEmpty()) {
         return 0;
      } else {
         Assert.a(var6.size() == 1);
         long var7 = (Long)var6.iterator().next();
         if (var7 == -1L) {
            return -1;
         } else {
            return var7 == -2L ? -2 : 1;
         }
      }
   }

   @Override
   public boolean checkNoUse(long var1, int var3, boolean var4) {
      return this.checkNoUse(var1, var3, var4, this.defaultMaxBlocks) == 0;
   }

   @Override
   public boolean checkNoUse(long var1, int var3) {
      return this.checkNoUse(var1, var3, true, this.defaultMaxBlocks) == 0;
   }

   public Long checkSingleUse(long var1, int var3, boolean var4) {
      Collection var5 = this.getDefUses(var1, var3, 2, true, var4, null, -1, this.defaultMaxBlocks, false);
      return var5 != null && var5.size() == 1 ? (Long)var5.iterator().next() : null;
   }

   @Override
   public Long checkSingleUse(long var1, int var3) {
      return this.checkSingleUse(var1, var3, true);
   }

   public Long checkSingleDef(long var1, int var3, boolean var4) {
      Collection var5 = this.getUseDefs(var1, var3, 2, true, var4, null, -1, null, false, this.defaultMaxBlocks, false);
      return var5 != null && var5.size() == 1 ? (Long)var5.iterator().next() : null;
   }

   @Override
   public Long checkSingleDefNoInput(long var1, int var3) {
      return this.checkSingleDef(var1, var3, false);
   }

   @Override
   public Long checkSingleDef(long var1, int var3) {
      return this.checkSingleDef(var1, var3, true);
   }

   @Override
   public Long checkSingleSource(long var1, int var3, boolean var4) {
      Collection var5 = this.getUseDefs(var1, var3, 2, false, var4, null, -1, null, false, this.defaultMaxBlocks, false);
      return var5 != null && var5.size() == 1 ? (Long)var5.iterator().next() : null;
   }

   @Override
   public Long checkSingleSource(long var1, int var3) {
      return this.checkSingleSource(var1, var3, true);
   }

   @Override
   public void collectInstructionAllDefs(long var1, Collection var3) {
      DUI var4 = this.getDUI(var1);
      var3.addAll(var4.def);
      var3.addAll(var4.defpot);
      var3.addAll(var4.spoiled);
   }

   @Override
   public void collectInstructionAllUses(long var1, Collection var3) {
      DUI var4 = this.getDUI(var1);
      var3.addAll(var4.use);
      var3.addAll(var4.usepot);
   }

   public Set getInstructionAllDefs(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.def);
      var4.addAll(var3.defpot);
      var4.addAll(var3.spoiled);
      return var4;
   }

   public Set getInstructionAllUses(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.use);
      var4.addAll(var3.usepot);
      return var4;
   }

   public Set getInstructionDefs(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.def);
      return var4;
   }

   public Set getInstructionUses(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.use);
      return var4;
   }

   public Set getInstructionPotentialDefs(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.defpot);
      return var4;
   }

   public Set getInstructionPotentialUses(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.usepot);
      return var4;
   }

   public Set getInstructionSpoiledDefs(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.spoiled);
      return var4;
   }

   private Set collectVarUses(IBasicBlock var1) {
      Object var2;
      if (var1 != null) {
         var2 = new LinkedHashSet();
         ArrayDeque var3 = new ArrayDeque();
         var3.add(var1);
         HashSet var4 = new HashSet();

         while (!var3.isEmpty()) {
            IBasicBlock var5 = (IBasicBlock)var3.remove();
            if (var4.add(var5.getBase())) {
               long var6 = var5.getBase();

               for (int var8 = 0; var8 < var5.size(); var8++) {
                  IInstruction var9 = var5.get(var8);
                  DUI var10 = this.getDUI(var6, var9);
                  var2.addAll(var10.use);
                  var2.addAll(var10.usepot);
                  var6 += var9.getSize();
               }

               var3.addAll(var5.getOutputs());
               var3.addAll(var5.getIrregularOutputs());
            }
         }
      } else {
         var2 = new HashSet();

         for (AddressableInstruction var12 : this.cfg.addressableInstructions()) {
            DUI var13 = this.getDUI(var12);
            var2.addAll(var13.use);
            var2.addAll(var13.usepot);
         }
      }

      return (Set)var2;
   }

   private Set collectVarUses() {
      return this.collectVarUses(null);
   }

   private Set collectVarDefs(IBasicBlock var1) {
      Object var2;
      if (var1 != null) {
         var2 = new LinkedHashSet();
         ArrayDeque var3 = new ArrayDeque();
         var3.add(var1);
         HashSet var4 = new HashSet();

         while (!var3.isEmpty()) {
            IBasicBlock var5 = (IBasicBlock)var3.remove();
            if (var4.add(var5.getBase())) {
               long var6 = var5.getEndAddress();

               for (int var8 = var5.size() - 1; var8 >= 0; var8--) {
                  IInstruction var9 = var5.get(var8);
                  var6 -= var9.getSize();
                  DUI var10 = this.getDUI(var6, var9);
                  var2.addAll(var10.def);
                  var2.addAll(var10.defpot);
                  var2.addAll(var10.spoiled);
               }

               var3.addAll(var5.getInputs());
               var3.addAll(var5.getIrregularInputs());
            }
         }
      } else {
         var2 = new HashSet();

         for (AddressableInstruction var12 : this.cfg.addressableInstructions()) {
            DUI var13 = this.getDUI(var12);
            var2.addAll(var13.def);
            var2.addAll(var13.defpot);
            var2.addAll(var13.spoiled);
         }
      }

      return (Set)var2;
   }

   private Set collectVarDefs() {
      return this.collectVarDefs(null);
   }

   public Map getUseDefChains(long var1, boolean var3) {
      Set var4 = this.getInstructionAllUses(var1);
      HashMap var5 = new HashMap();

      for (int var7 : var4) {
         Collection var8 = this.getUseDefs(var1, var7, -1, false, var3, null, -1, null, false, this.defaultMaxBlocks, false);
         var5.put(var7, var8);
      }

      return var5;
   }

   @Override
   public Map getUseDefChains(long var1) {
      return this.getUseDefChains(var1, true);
   }

   public Map getDefUseChains(long var1, boolean var3) {
      DUI var4 = this.getDUI(var1);
      HashSet var5 = new HashSet();
      var5.addAll(var4.def);
      var5.addAll(var4.defpot);
      HashMap var6 = new HashMap();

      for (int var8 : var5) {
         Collection var9 = this.getDefUses(var1, var8, -1, false, var3, null, -1, this.defaultMaxBlocks, false);
         var6.put(var8, var9);
      }

      return var6;
   }

   @Override
   public Map getDefUseChains(long var1) {
      return this.getDefUseChains(var1, false);
   }

   public Map getLiveChains(IBasicBlock var1, int var2, Collection var3, int var4) {
      if (var3 == null) {
         var3 = this.collectVarUses();
      }

      HashMap var5 = new HashMap();

      for (int var7 : var3) {
         Collection var8 = this.getDefUses(-1L, var7, var4, false, false, var1, var2, this.defaultMaxBlocks, false);
         if (!var8.isEmpty()) {
            var5.put(var7, var8);
         }
      }

      return var5;
   }

   @Override
   public Collection getLiveChains(IBasicBlock var1, int var2, int var3) {
      return (Collection)this.getLiveChains(var1, var2, new ArrayList1(var3), -1).getOrDefault(var3, Collections.emptySet());
   }

   @Override
   public Collection getLiveChains(IBasicBlock var1, int var2, int var3, int var4) {
      return (Collection)this.getLiveChains(var1, var2, new ArrayList1(var3), var4).getOrDefault(var3, Collections.emptySet());
   }

   @Override
   public Map getLiveChains(IBasicBlock var1, int var2) {
      return this.getLiveChains(var1, var2, null, -1);
   }

   @Override
   public boolean isAlive(IBasicBlock var1, int var2, int var3) {
      return !this.getDefUses(-1L, var3, 1, false, false, var1, var2, this.defaultMaxBlocks, false).isEmpty();
   }

   public Map getReachChains(IBasicBlock var1, int var2, Collection var3, int var4) {
      HashMap var5 = new HashMap();
      if (var2 == var1.size() && this.isTerminator(var1)) {
         return var5;
      } else {
         Collection var6 = null;
         if (this.integrateCalculatedInputRegisters) {
            var6 = this.getInputs();
         }

         if (var3 == null) {
            var3 = this.collectVarDefs();
            if (var6 != null) {
               var3.addAll(var6);
            }
         }

         for (int var8 : var3) {
            boolean var9 = var6 != null && var6.contains(var8);
            Collection var10 = this.getUseDefs(-1L, var8, var4, false, var9, var1, var2, null, false, this.defaultMaxBlocks, false);
            if (!var10.isEmpty()) {
               var5.put(var8, var10);
            }
         }

         return var5;
      }
   }

   @Override
   public Collection getReachChains(IBasicBlock var1, int var2, int var3) {
      return (Collection)this.getReachChains(var1, var2, new ArrayList1(var3), -1).getOrDefault(var3, Collections.emptySet());
   }

   @Override
   public Collection getReachChains(IBasicBlock var1, int var2, int var3, int var4) {
      return (Collection)this.getReachChains(var1, var2, new ArrayList1(var3), var4).getOrDefault(var3, Collections.emptySet());
   }

   @Override
   public Map getReachChains(IBasicBlock var1, int var2) {
      return this.getReachChains(var1, var2, null, -1);
   }

   @Override
   public boolean isReaching(IBasicBlock var1, int var2, int var3) {
      return !this.getUseDefs(-1L, var3, 1, false, true, var1, var2, null, false, this.defaultMaxBlocks, false).isEmpty();
   }

   @Override
   public Collection getUseDiscrepancies(long var1) {
      DUI var3 = this.getDUI(var1);
      HashSet var4 = new HashSet();
      var4.addAll(var3.use);
      var4.addAll(var3.usepot);
      boolean[] var5 = new boolean[1];
      ArrayList var6 = new ArrayList();

      for (int var8 : var4) {
         var5[0] = false;
         Collection var9 = this.getUseDefs(var1, var8, -1, false, true, null, -1, var5, false, this.defaultMaxBlocks, false);
         if (var5[0] || var9.isEmpty()) {
            var6.add(var8);
         }
      }

      return var6;
   }

   @Override
   public boolean isUseDiscrepancy(long var1, int var3) {
      boolean[] var4 = new boolean[1];
      Collection var5 = this.getUseDefs(var1, var3, -1, true, true, null, -1, var4, false, this.defaultMaxBlocks, false);
      return var4[0] || var5.isEmpty();
   }

   public boolean checkInput(int var1) {
      return this.filterInputs(new ArrayList1(var1)).contains(var1);
   }

   @Override
   public Collection getInputs() {
      return this.filterInputs(null);
   }

   public Collection filterInputs(Collection var1) {
      IBasicBlock var2 = this.cfg.getEntryBlock();
      if (var1 == null) {
         var1 = this.collectVarUses(var2);
      }

      ArrayList var3 = new ArrayList();

      for (int var5 : var1) {
         Collection var6 = this.getDefUses(-1L, var5, 1, false, false, var2, 0, this.defaultMaxBlocks, false);
         if (!var6.isEmpty()) {
            var3.add(0, var5);
         }
      }

      return var3;
   }

   public Map getInputsWithUses() {
      return this.filterInputsWithUses(null);
   }

   public Collection checkInputWithUses(int var1) {
      return (Collection)this.filterInputsWithUses(new ArrayList1(var1)).getOrDefault(var1, Collections.emptySet());
   }

   public Map filterInputsWithUses(Collection var1) {
      IBasicBlock var2 = this.cfg.getEntryBlock();
      if (var1 == null) {
         var1 = this.collectVarUses();
      }

      HashMap var3 = new HashMap();

      for (int var5 : var1) {
         Collection var6 = this.getDefUses(-1L, var5, -1, false, false, var2, 0, this.defaultMaxBlocks, false);
         if (!var6.isEmpty()) {
            var3.put(var5, var6);
         }
      }

      return var3;
   }

   @Override
   public Map getInputMap() {
      return this.getInputsWithUses();
   }

   @Override
   public Collection getInputMap(int var1) {
      return this.checkInputWithUses(var1);
   }

   public boolean checkOutput(IBasicBlock var1, int var2) {
      return this.filterOutputs(var1, new ArrayList1(var2)).contains(var2);
   }

   @Override
   public Collection getOutputs(IBasicBlock var1) {
      return this.filterOutputs(var1, null);
   }

   public Collection filterOutputs(IBasicBlock var1, Collection var2) {
      ArrayList var3 = new ArrayList();
      if (this.isTerminator(var1)) {
         return var3;
      } else {
         Collection var4 = null;
         if (this.integrateCalculatedInputRegisters) {
            var4 = this.getInputs();
         }

         if (var2 == null) {
            var2 = this.collectVarDefs(var1);
            if (var4 != null) {
               var2.addAll(var4);
            }
         }

         for (int var6 : var2) {
            boolean var7 = var4 != null && var4.contains(var6);
            Collection var8 = this.getUseDefs(-1L, var6, 1, false, var7, var1, var1.size(), null, true, this.defaultMaxBlocks, false);
            if (!var8.isEmpty()) {
               var3.add(0, var6);
            }
         }

         return var3;
      }
   }

   public Map getOutputsWithDefs(IBasicBlock var1) {
      return this.filterOutputsWithDefs(var1, null);
   }

   public Collection checkOutputsWithDefs(IBasicBlock var1, int var2) {
      return (Collection)this.filterOutputsWithDefs(var1, new ArrayList1(var2)).getOrDefault(var2, Collections.emptySet());
   }

   public Map filterOutputsWithDefs(IBasicBlock var1, Collection var2) {
      HashMap var3 = new HashMap();
      if (this.isTerminator(var1)) {
         return var3;
      } else {
         Collection var4 = null;
         if (this.integrateCalculatedInputRegisters) {
            var4 = this.getInputs();
         }

         if (var2 == null) {
            var2 = this.collectVarDefs();
            if (var4 != null) {
               var2.addAll(var4);
            }
         }

         for (int var6 : var2) {
            boolean var7 = var4 != null && var4.contains(var6);
            Collection var8 = this.getUseDefs(-1L, var6, -1, false, var7, var1, var1.size(), null, true, this.defaultMaxBlocks, false);
            if (!var8.isEmpty()) {
               var3.put(var6, var8);
            }
         }

         return var3;
      }
   }

   @Override
   public Map getOutputMap(IBasicBlock var1) {
      return this.getOutputsWithDefs(var1);
   }

   @Override
   public Collection getOutputMap(IBasicBlock var1, int var2) {
      return this.checkOutputsWithDefs(var1, var2);
   }

   public boolean checkOutput(int var1) {
      return this.filterOutputs(new ArrayList1(var1)).contains(var1);
   }

   @Override
   public Collection getOutputs() {
      return this.filterOutputs(null);
   }

   public Collection filterOutputs(Collection var1) {
      Collection var2 = null;
      if (this.integrateCalculatedInputRegisters) {
         var2 = this.getInputs();
      }

      if (var1 == null) {
         var1 = this.collectVarDefs();
         if (var2 != null) {
            var1.addAll(var2);
         }
      }

      HashSet var3 = new HashSet();

      for (IBasicBlock var5 : this.cfg.getExitBlocks()) {
         if (!this.isTerminator(var5)) {
            for (int var7 : var1) {
               boolean var8 = var2 != null && var2.contains(var7);
               Collection var9 = this.getUseDefs(-1L, var7, 1, false, var8, var5, var5.size(), null, true, this.defaultMaxBlocks, false);
               if (!var9.isEmpty()) {
                  var3.add(var7);
               }
            }

            if (var1.removeAll(var3) && var1.isEmpty()) {
               break;
            }
         }
      }

      return var3;
   }

   public Map getOutputsWithDefs() {
      return this.filterOutputsWithDefs(null);
   }

   public Collection checkOutputsWithDefs(int var1) {
      return (Collection)this.filterOutputsWithDefs(new ArrayList1(var1)).getOrDefault(var1, Collections.emptySet());
   }

   public Map filterOutputsWithDefs(Collection var1) {
      Collection var2 = null;
      if (this.integrateCalculatedInputRegisters) {
         var2 = this.getInputs();
      }

      if (var1 == null) {
         var1 = this.collectVarDefs();
         if (var2 != null) {
            var1.addAll(var2);
         }
      }

      HashMap var3 = new HashMap();

      for (IBasicBlock var5 : this.cfg.getExitBlocks()) {
         if (!this.isTerminator(var5)) {
            for (int var7 : var1) {
               boolean var8 = var2 != null && var2.contains(var7);
               Collection var9 = this.getUseDefs(-1L, var7, -1, false, var8, var5, var5.size(), null, true, this.defaultMaxBlocks, false);
               if (!var9.isEmpty()) {
                  Object var10 = (Collection)var3.get(var7);
                  if (var10 == null) {
                     var10 = new HashSet();
                     var3.put(var7, var10);
                  }

                  var10.addAll(var9);
               }
            }
         }
      }

      return var3;
   }

   @Override
   public Map getOutputMap() {
      return this.getOutputsWithDefs();
   }

   @Override
   public Collection getOutputMap(int var1) {
      return this.checkOutputsWithDefs(var1);
   }

   public boolean isNotRedefined(int var1, long var2, long var4) {
      Couple var6 = this.cfg.getInstructionLocation(var2);
      Couple var7 = this.cfg.getInstructionLocation(var4);
      return this.isNotRedefined(var1, (IBasicBlock)var6.getFirst(), (Integer)var6.getSecond(), (IBasicBlock)var7.getFirst(), (Integer)var7.getSecond());
   }

   public boolean isNotRedefined(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5) {
      return this.isVarReachingFromTo(var1, var2, var3, var4, var5, false, false, -1);
   }

   @Override
   public Boolean isVarReachingFromTo(int var1, long var2, long var4) {
      Couple var6 = this.cfg.getInstructionLocation(var2);
      Couple var7 = this.cfg.getInstructionLocation(var4);
      return this.isVarReachingFromTo(var1, (IBasicBlock)var6.getFirst(), (Integer)var6.getSecond(), (IBasicBlock)var7.getFirst(), (Integer)var7.getSecond());
   }

   @Override
   public Boolean isVarReachingFromTo(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5) {
      return this.isVarReachingFromTo(var1, var2, var3, var4, var5, true, -1);
   }

   @Override
   public Boolean isVarReachingFromTo(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5, boolean var6, int var7) {
      return this.isVarReachingFromTo(var1, var2, var3, var4, var5, true, var6, var7);
   }

   public Boolean isVarReachingFromTo(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5, boolean var6, boolean var7, int var8) {
      DFA4.VRCacheKey var9 = null;
      Boolean var10 = null;
      if (this.cachecfg >= 2) {
         if (var6 && var7 && var8 == -1) {
            var9 = new DFA4.VRCacheKey(var2.getBase(), var3, var4.getBase(), var5, var1);
         }

         if (var9 != null) {
            if (this.vrCache == null) {
               this.vrCache = new CacheMap(1000);
            } else {
               var10 = (Boolean)this.vrCache.get(var9);
            }
         }
      }

      if (var10 == null) {
         var10 = this.isVarReachingFromTo_INTERNAL(var1, var2, var3, var4, var5, var6, var7, var8);
         if (var9 != null && var10 != null) {
            this.vrCache.put(var9, var10);
         }
      }

      return var10;
   }

   private Boolean isVarReachingFromTo_INTERNAL(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5, boolean var6, boolean var7, int var8) {
      ArrayDeque var9 = new ArrayDeque();
      var9.add(var2);
      HashSet var10 = new HashSet();
      ArrayList var11 = new ArrayList();
      int var12 = 0;
      int var13 = var3 + 1;

      while (!var9.isEmpty()) {
         IBasicBlock var14 = (IBasicBlock)var9.remove();
         if (var12 <= 0 || var10.add(var14.getBase())) {
            if (var8-- == 0) {
               return null;
            }

            int var15 = var14.size();
            if (var14 == var4) {
               var15 = var5;
            }

            long var16 = var13 < var15 ? var14.getAddressOfInstruction(var13) : -1L;

            while (true) {
               if (var13 < var15) {
                  IInstruction var18 = var14.get(var13);
                  DUI var19 = this.getDUI(var16, var18);
                  if (!var19.def.contains(var1) && !var19.defpot.contains(var1) && !var19.spoiled.contains(var1)) {
                     var16 += var18.getSize();
                     var13++;
                     continue;
                  }

                  if (!var6 || var14 == var2 || var14 == var4) {
                     return false;
                  }

                  var11.add(var14);
               }

               var13 = 0;
               if (var14 != var4) {
                  var9.addAll(var14.getOutputs());
                  var9.addAll(var14.getIrregularOutputs());
               }

               var12++;
               break;
            }
         }
      }

      if (!var11.isEmpty() && !this.verifyNoRedef(var2, var4, var11)) {
         return false;
      } else {
         return var7 ? this.verifyNoRedefAfterSink(var1, var2, var3, var4, var5, var8) : true;
      }
   }

   private boolean verifyNoRedef(IBasicBlock var1, IBasicBlock var2, List var3) {
      HashSet var4 = new HashSet();

      for (IBasicBlock var6 : var3) {
         ArrayDeque var7 = new ArrayDeque();
         var7.add(var6);
         var4.add(var6.getBase());

         while (!var7.isEmpty()) {
            IBasicBlock var8 = (IBasicBlock)var7.remove();
            List[] var9 = new List[]{var8.getOutputs(), var8.getIrregularOutputs()};

            for (List var13 : var9) {
               if (var13 != null) {
                  for (IBasicBlock var15 : var13) {
                     if (var15 != var1) {
                        if (var15 == var2) {
                           return false;
                        }

                        if (var4.add(var15.getBase())) {
                           var7.add(var15);
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public Boolean verifyNoRedefAfterSink(int var1, long var2, long var4) {
      Couple var6 = this.cfg.getInstructionLocation(var2);
      Couple var7 = this.cfg.getInstructionLocation(var4);
      return this.verifyNoRedefAfterSink(
         var1, (IBasicBlock)var6.getFirst(), (Integer)var6.getSecond(), (IBasicBlock)var7.getFirst(), (Integer)var7.getSecond(), -1
      );
   }

   public Boolean verifyNoRedefAfterSink(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5, int var6) {
      if (var4 == var2) {
         return true;
      } else {
         boolean var7 = false;
         ArrayDeque var8 = new ArrayDeque();
         var8.add(var4);
         HashSet var9 = new HashSet();
         int var10 = 0;

         label86:
         while (!var8.isEmpty()) {
            IBasicBlock var11 = (IBasicBlock)var8.remove();
            if (var10 <= 0 || var9.add(var11.getBase())) {
               if (var6-- == 0) {
                  return null;
               }

               if (var11 != var2) {
                  int var12 = var10 == 0 ? var5 + 1 : 0;

                  for (long var13 = var12 < var11.size() ? var11.getAddressOfInstruction(var12) : -1L; var12 < var11.size(); var12++) {
                     IInstruction var15 = var11.get(var12);
                     DUI var16 = this.getDUI(var13, var15);
                     if (var16.def.contains(var1) || var16.defpot.contains(var1) || var16.spoiled.contains(var1)) {
                        var7 = true;
                        break label86;
                     }

                     var13 += var15.getSize();
                  }

                  var8.addAll(var11.getOutputs());
                  var8.addAll(var11.getIrregularOutputs());
               }

               var10++;
            }
         }

         if (var7) {
            var8.clear();
            var8.add(var4);
            var9.clear();
            var10 = 0;

            while (!var8.isEmpty()) {
               IBasicBlock var18 = (IBasicBlock)var8.remove();
               if (var10 > 0 && var18 == var4) {
                  return false;
               }

               if (var9.add(var18.getBase())) {
                  if (var6-- == 0) {
                     return null;
                  }

                  if (var18 != var2) {
                     var8.addAll(var18.getOutputs());
                     var8.addAll(var18.getIrregularOutputs());
                  }

                  var10++;
               }
            }
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("DFA{");
      Strings.ff(var1, "settings:collFlags=%s,integrateInputs=%b", this.collectionFlagsAsString(), this.isIntegrateCalculatedInputRegisters());
      var1.append("}");
      return var1.toString();
   }

   private String collectionFlagsAsString() {
      switch (this.varCollectionFlags) {
         case 1:
            return "POTENTIALS";
         case 2:
            return "SPOILED";
         case 3:
            return "CONSERVATIVE";
         default:
            return Strings.ff("0x%X", this.varCollectionFlags);
      }
   }

   static class CacheKey {
      final long blkaddr;
      final int insnidx;
      final long insnaddr;
      final int varid;

      CacheKey(long var1, int var3) {
         this.blkaddr = -1L;
         this.insnidx = -1;
         this.insnaddr = var1;
         this.varid = var3;
      }

      CacheKey(long var1, int var3, int var4) {
         this.blkaddr = var1;
         this.insnidx = var3;
         this.insnaddr = -1L;
         this.varid = var4;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.blkaddr ^ this.blkaddr >>> 32);
         var1 = 31 * var1 + (int)(this.insnaddr ^ this.insnaddr >>> 32);
         var1 = 31 * var1 + this.insnidx;
         return 31 * var1 + this.varid;
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
            DFA4.CacheKey var2 = (DFA4.CacheKey)var1;
            if (this.blkaddr != var2.blkaddr) {
               return false;
            } else if (this.insnaddr != var2.insnaddr) {
               return false;
            } else {
               return this.insnidx != var2.insnidx ? false : this.varid == var2.varid;
            }
         }
      }
   }

   static class VRCacheKey {
      final long blk0addr;
      final int idx0;
      final long blk1addr;
      final int idx1;
      final int varid;

      VRCacheKey(long var1, int var3, long var4, int var6, int var7) {
         this.blk0addr = var1;
         this.idx0 = var3;
         this.blk1addr = var4;
         this.idx1 = var6;
         this.varid = var7;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.blk0addr ^ this.blk0addr >>> 32);
         var1 = 31 * var1 + (int)(this.blk1addr ^ this.blk1addr >>> 32);
         var1 = 31 * var1 + this.idx0;
         var1 = 31 * var1 + this.idx1;
         return 31 * var1 + this.varid;
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
            DFA4.VRCacheKey var2 = (DFA4.VRCacheKey)var1;
            if (this.blk0addr != var2.blk0addr) {
               return false;
            } else if (this.blk1addr != var2.blk1addr) {
               return false;
            } else if (this.idx0 != var2.idx0) {
               return false;
            } else {
               return this.idx1 != var2.idx1 ? false : this.varid == var2.varid;
            }
         }
      }
   }
}
