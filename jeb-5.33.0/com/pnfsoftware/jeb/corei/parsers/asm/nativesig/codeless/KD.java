package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Constraint;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ExecutableModelMetadata;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.MatchingState;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Module;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ModuleId;
import com.pnfsoftware.jeb.util.collect.BiMap;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.SetMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class KD {
   static final ILogger pC = GlobalLog.getLogger(KD.class);
   public static final String A = null;
   Set kS = new HashSet();
   SetMap wS = new SetMap();
   SetMap UT = new SetMap();
   SetMap E = new SetMap();
   SetMap sY = new SetMap();
   SetMap ys = new SetMap();
   List ld = new ArrayList();
   Set gp = new HashSet();
   long oT;
   long fI;

   public MatchingState pC(String var1, INativeCodeUnit var2) {
      Ws var3 = Ws.pC(var1);
      if (var3 == null) {
         pC.error("> cannot open reference model");
         return null;
      } else {
         Ws var4 = new Ws();
         yt var5 = new yt();
         var5.pC(var2, var4, false, true);
         return this.pC(var3, var4, null, false, false, false, 0);
      }
   }

   public MatchingState pC(String var1, String var2, boolean var3, boolean var4, boolean var5, int var6) {
      Ws var7 = Ws.pC(var1);
      if (var7 == null) {
         pC.error("> cannot open reference model");
         return null;
      } else {
         Ws var8 = Ws.pC(var2);
         if (var8 == null) {
            pC.error("> cannot open target model");
            return null;
         } else {
            return this.pC(var7, var8, var2, var3, var4, var5, var6);
         }
      }
   }

   public MatchingState pC(Ws var1, Ws var2, String var3, boolean var4, boolean var5, boolean var6, int var7) {
      MatchingState var8 = this.pC(var1, var2, var6, var7);
      StringBuilder var9 = new StringBuilder();
      var9.append(Strings.ff("%s %s matching results:", var1.fI().getLibraryId(), var1.fI().getLibraryVersion()));
      var9.append(Strings.LINESEP);
      var9.append(Strings.LINESEP);
      if (var8.getIdentifiedRoutines().isEmpty()) {
         var9.append(Strings.ff("   --> no matches"));
         var8.setResultLog(var9.toString());
         return var8;
      } else {
         this.oT = Long.MAX_VALUE;
         this.fI = 0L;

         for (Func var11 : var2.pC()) {
            if (var11.getTrampolineTarget() == null) {
               Func var12 = (Func)var8.getIdentifiedRoutines().get(var11);
               if (var11.isMapped() && var12 != null && !var12.getModuleId().isUnknown()) {
                  this.oT = Math.min(this.oT, var11.getLowestAddress());
                  this.fI = Math.max(this.fI, var11.getHighestAddress());
               }
            }
         }

         var9.append(Strings.ff("   -> library range: [0x%08x-0x%08x]", this.oT, this.fI));
         var9.append(Strings.LINESEP);
         var9.append(Strings.LINESEP);
         var8.setIdentifiedRoutinesRange(this.oT, this.fI);
         long var17 = var2.pC().stream().filter(var1x -> var1x.isMapped() && this.pC(var1x)).count();
         var9.append(
            Strings.ff(
               "   -> identified routines in library range: %d/%d (%.2f%%)",
               var8.getIdentifiedRoutines().size(),
               var17,
               var8.getIdentifiedRoutines().size() * 100.0F / (float)var17
            )
         );
         var9.append(Strings.LINESEP);
         var9.append(Strings.LINESEP);
         long var18 = 0L;

         for (Entry var15 : var8.getRoutinesConstraints().entrySet()) {
            if (((List)var15.getValue()).size() == 1 && ((Constraint)((List)var15.getValue()).get(0)).getPossibleFuncs().size() <= 3) {
               var18++;
            }
         }

         var9.append(Strings.ff("   -> almost identified routines (max 3 possible names): %d/%d (%.2f%%)", var18, var17, (float)var18 * 100.0F / (float)var17));
         var9.append(Strings.LINESEP);
         var9.append(Strings.LINESEP);
         if (var3 != null && var4) {
            this.pC(var8, var2, var3);
         }

         if (var3 != null && var5) {
            try {
               MatchingState.serialize(var8, new File(var3).getParent());
            } catch (IOException var16) {
               pC.error("> ser failed");
            }
         }

         String var19 = var9.toString();
         pC.info(var19);
         var8.setResultLog(var19);
         return var8;
      }
   }

   private void pC(MatchingState var1, Ws var2, String var3) {
      long var4 = System.currentTimeMillis() / 1000L;
      HashSet var6 = new HashSet();
      StringBuilder var7 = new StringBuilder();
      ExecutableModelMetadata var8 = var1.getRefMetadata();
      String var9 = var8.getLibraryId().getName();
      var7.append("{");

      for (Func var11 : var1.getIdentifiedRoutines().keySet()) {
         Func var12 = (Func)var1.getIdentifiedRoutines().get(var11);
         String var13 = var12.getModuleId().isUnknown() ? "unk::unk" : var9 + "::" + var12.getModuleId().getFileName();
         var7.append(Strings.ff("\"%s\": \"%s%s%s\",", var11.getEntryPoint(), var13, "::", var12.getName()));
         var6.add(var11.getEntryPoint());
      }

      long var25 = (Long)var1.getIdentifiedRoutinesRange().getFirst();
      long var26 = (Long)var1.getIdentifiedRoutinesRange().getSecond();

      for (Entry var15 : var1.getRoutinesConstraints().entrySet()) {
         Func var16 = (Func)var15.getKey();
         if (var16.getHighestAddress() >= var25 && var16.getHighestAddress() <= var26 && var16.getLowestAddress() >= var25 && var16.getLowestAddress() <= var26
            )
          {
            HashSet var17 = new HashSet();

            for (Constraint var19 : (List)var15.getValue()) {
               var17.addAll(var19.getPossibleFuncs());
            }

            Set var35 = (Set)var17.stream().map(var0 -> var0.getModuleId()).collect(Collectors.toSet());
            boolean var20 = true;
            String var37;
            if (var35.size() == 1) {
               ModuleId var21 = (ModuleId)var35.iterator().next();
               if (var21.isUnknown()) {
                  var37 = "unk::unk";
                  var20 = false;
               } else {
                  var37 = var9 + "::" + var21.getFileName();
               }
            } else {
               var37 = var9 + "::unk";
            }

            if (var20) {
               var7.append(Strings.ff("\"%s\": \"%s\",", var16.getEntryPoint(), var37 + "::unk"));
            }

            var6.add(var16.getEntryPoint());
         }
      }

      var7.append("}");

      try {
         String var27 = Strings.ff("identified_routines_%x_%d.json", var4, var1.getIdentifiedRoutines().size());
         IO.writeFile(new File(new File(var3).getParent().concat(File.separator).concat("results").concat(File.separator).concat(var27)), var7.toString());
      } catch (IOException var24) {
         pC.error("> error: failed to write results...");
      }

      StringBuilder var28 = new StringBuilder();
      var28.append("{");

      for (Entry var32 : var1.getRoutinesConstraints().entrySet()) {
         var28.append(Strings.ff("\"%s\":[", ((Func)var32.getKey()).getName()));

         for (Constraint var36 : (List)var32.getValue()) {
            var28.append("[");

            for (Func var39 : var36.getPossibleFuncs()) {
               Strings.ff(var28, "\"%s\",", var39.getName());
            }

            var28.deleteCharAt(var28.length() - 1);
            var28.append("]");
         }

         var28.append("],");
      }

      var28.deleteCharAt(var28.length() - 1);
      var28.append("}");

      try {
         String var30 = Strings.ff("routines_constraints_%x_%d.json", var4, var1.getRoutinesConstraints().size());
         IO.writeFile(new File(new File(var3).getParent().concat(File.separator).concat("results").concat(File.separator).concat(var30)), var28.toString());
      } catch (IOException var23) {
         pC.error("> error: failed to write results...");
      }

      StringBuilder var31 = new StringBuilder();
      var31.append(var1.getTargetModulesMapping().toString());

      try {
         String var33 = Strings.ff("modules_map_%x_%d.txt", var4, var1.getTargetModulesMapping().size());
         IO.writeFile(new File(new File(var3).getParent().concat(File.separator).concat("results").concat(File.separator).concat(var33)), var31.toString());
      } catch (IOException var22) {
         pC.error("> error: failed to write results...");
      }
   }

   private MatchingState pC(Ws var1, Ws var2, boolean var3, int var4) {
      this.pC(var1, var3);
      MatchingState var5 = new MatchingState();
      var5.setRefMetadata(var1.fI());
      if (!this.kS(var1, var2, var5, var3)) {
         return var5;
      } else {
         if (var3) {
            Object[] var10000 = new Object[]{var5.getIdentifiedRoutines().size()};
            this.pC(var5.getIdentifiedRoutines());
         }

         Object var6 = new ArrayList(var5.getIdentifiedRoutines().keySet());
         int var7 = 0;

         while (!var6.isEmpty()) {
            var7++;
            if (var3) {
               Object[] var20 = new Object[]{var7, var6.size()};
            }

            if (var4 != 0 && var7 >= var4) {
               if (var3) {
                  Object[] var26 = new Object[0];
               }
               break;
            }

            this.pC((List)var6, var1, var2, var5, var3);
            this.A((List)var6, var1, var2, var5, var3);
            this.A(var1, var2, var5, var3);
            if (var3) {
               this.pC(var5);
               Object[] var21 = new Object[]{var5.getRoutinesConstraints().size()};
            }

            this.pC(var1, var2, var5, var3);
            if (var3) {
               this.pC(var5);
               Object[] var22 = new Object[]{var5.getRoutinesConstraints().size()};
            }

            this.kS((List)var6, var1, var2, var5, var3);
            if (var3) {
               this.pC(var5);
            }

            if (var3) {
               Object[] var23 = new Object[]{var5.getRoutinesConstraints().size()};
            }

            this.wS((List)var6, var1, var2, var5, var3);
            if (var3) {
               this.pC(var5);
               Object[] var24 = new Object[]{var5.getRoutinesConstraints().size()};
            }

            var6 = this.pC(var1, var2, var5, true, var3);
            if (var3) {
               Object[] var25 = new Object[]{var6.size()};
               this.pC(var5);
            }
         }

         HashSet var8 = new HashSet();

         for (long var10 : var5.getTargetModulesMapping().keySet()) {
            Module var12 = (Module)var5.getTargetModulesMapping().get(var10);
            Set var13 = (Set)var2.wS
               .values()
               .stream()
               .filter(var1x -> var1x.getLowestAddress() != null && var1x.getLowestAddress() >= var12.getBegin() && var1x.getLowestAddress() <= var12.getEnd())
               .collect(Collectors.toSet());
            if (var13.size() != 1 || var1.wS.get(var12.getId()).size() <= 1) {
               break;
            }

            if (var3) {
               Object[] var27 = new Object[]{var12.getName()};
            }

            var8.addAll(var13);
         }

         for (long var16 : var5.getTargetModulesMapping().descendingKeySet()) {
            Module var18 = (Module)var5.getTargetModulesMapping().get(var16);
            Set var19 = (Set)var2.wS
               .values()
               .stream()
               .filter(var1x -> var1x.getLowestAddress() != null && var1x.getLowestAddress() >= var18.getBegin() && var1x.getLowestAddress() <= var18.getEnd())
               .collect(Collectors.toSet());
            if (var19.size() != 1 || var1.wS.get(var18.getId()).size() <= 1) {
               break;
            }

            if (var3) {
               Object[] var28 = new Object[]{var18.getName()};
            }

            var8.addAll(var19);
         }

         for (Func var17 : var8) {
            var5.getIdentifiedRoutines().remove(var17);
         }

         return var5;
      }
   }

   private void pC(List var1, Ws var2, Ws var3, MatchingState var4, boolean var5) {
      ArrayList var6 = new ArrayList();

      for (Func var8 : var1) {
         Func var9 = (Func)var4.getIdentifiedRoutines().get(var8);
         if (this.ld.contains(var9)) {
            if (var5) {
               Object[] var10000 = new Object[]{var8, var9};
            }

            var6.add(var8);
         }
      }

      var1.removeAll(var6);
   }

   private void A(List var1, Ws var2, Ws var3, MatchingState var4, boolean var5) {
      ArrayList var6 = new ArrayList();

      for (Func var8 : var1) {
         if (var8.getTrampolineTarget() == null) {
            Func var9 = (Func)var4.getIdentifiedRoutines().get(var8);
            if (var8.getLowestAddress() != null && var8.getHighestAddress() != null) {
               long var10 = var8.getLowestAddress();
               long var12 = var8.getHighestAddress();
               if (!var9.getModuleId().isUnknown()) {
                  Module var14 = (Module)var4.getTargetModules().get(var9.getModuleId());
                  if (var14 == null) {
                     var14 = Module.createKnownModule(var9.getModuleId(), var10, var12);

                     try {
                        var4.getTargetModulesMapping().put((long)var10, (ISegment)var14);
                        var4.getTargetModules().put(var14.getId(), var14);
                     } catch (IllegalArgumentException var20) {
                        if (var5) {
                           Object[] var26 = new Object[]{var8, var9};
                        }

                        var6.add(var8);
                     }
                  } else {
                     long var15 = Math.min(var14.getBegin(), var10);
                     long var17 = Math.max(var14.getEnd(), var12);
                     Module var19 = Module.createKnownModule(var14.getId(), var15, var17);
                     var4.getTargetModulesMapping().remove(var14.getBegin());

                     try {
                        var4.getTargetModulesMapping().put((long)var15, (ISegment)var19);
                        var4.getTargetModules().put(var14.getId(), var19);
                     } catch (IllegalArgumentException var21) {
                        var4.getTargetModulesMapping().put((Comparable)var14.getBegin(), (ISegment)var14);
                        if (var5) {
                           Object[] var25 = new Object[]{var8, var9};
                        }

                        var6.add(var8);
                     }
                  }
               } else if (var5) {
                  Object[] var27 = new Object[]{var9};
               }
            } else if (var5) {
               Object[] var10000 = new Object[]{var8.getName()};
            }
         }
      }

      var1.removeAll(var6);

      for (Func var23 : var6) {
         var4.getIdentifiedRoutines().remove(var23);
      }

      var4.getNonIdentifiableRoutines().addAll(var6);
   }

   private List pC(Ws var1, Ws var2, MatchingState var3, boolean var4, boolean var5) {
      if (var5) {
         Object[] var10000 = new Object[0];
      }

      ArrayList var6 = new ArrayList();
      HashMap var7 = new HashMap();

      for (Func var9 : var3.getRoutinesConstraints().keySet()) {
         if (A != null && var9.getName().equals(A)) {
            Object[] var39 = new Object[0];
            this.pC((List)var3.getRoutinesConstraints().get(var9));
         }

         if (!var3.getNonIdentifiableRoutines().contains(var9)) {
            if (!var3.getIdentifiedRoutines().containsKey(var9)) {
               List var10 = (List)var3.getRoutinesConstraints().get(var9);

               for (Constraint var12 : var10) {
                  var12.getPossibleFuncs().removeIf(var1x -> var3.getIdentifiedRoutines().containsValue(var1x));
               }

               var10.removeIf(var0 -> var0.isEmpty());
               if (var10.isEmpty()) {
                  if (var5) {
                     Object[] var43 = new Object[]{var9};
                  }
               } else {
                  Set var24 = ((Constraint)var10.get(0)).getPossibleFuncs();

                  for (Constraint var13 : var10.subList(1, var10.size())) {
                     var24.retainAll(var13.getPossibleFuncs());
                  }

                  if (var4) {
                     HashSet var26 = new HashSet();
                     Module var28 = (Module)var3.getTargetModulesMapping().getSegmentAfter(var9.getHighestAddress());
                     Module var14 = (Module)var3.getTargetModulesMapping().getSegmentBefore(var9.getLowestAddress());

                     for (Func var16 : var24) {
                        ModuleId var17 = var16.getModuleId();
                        Module var18 = (Module)var3.getTargetModules().get(var17);
                        if (var18 != null && (var9.getLowestAddress() < var18.getBegin() || var9.getHighestAddress() > var18.getEnd())) {
                           boolean var19 = var28 != null && var28.getId().equals(var17);
                           boolean var20 = var14 != null && var14.getId().equals(var17);
                           if (!var19 && !var20) {
                              var26.add(var16);
                           }
                        }
                     }

                     var24.removeAll(var26);
                  }

                  Set var27 = var2.UT.get(var9);
                  if (var27 != null && !var27.isEmpty()) {
                     Set var29 = (Set)var27.stream()
                        .filter(var0 -> var0.getModuleId().isUnknown() && var0.getName().contains(".dll!"))
                        .map(var0 -> var0.getName().split(".dll!")[1])
                        .collect(Collectors.toSet());
                     if (var29 != null && !var29.isEmpty()) {
                        HashSet var32 = new HashSet();

                        for (Func var34 : var24) {
                           int var35 = 0;
                           if (this.wS.get(var34) != null) {
                              Set var36 = (Set)this.wS.get(var34).stream().map(var0 -> var0.getName()).collect(Collectors.toSet());
                              if (var36 != null) {
                                 for (String var38 : var29) {
                                    for (String var22 : var36) {
                                       if (var22.contains(var38)) {
                                          var35++;
                                          break;
                                       }
                                    }
                                 }
                              }
                           }

                           if (var35 != var29.size()) {
                              if (var5) {
                                 Object[] var40 = new Object[]{var9, var34};
                              }

                              var32.add(var34);
                           }
                        }

                        var24.removeAll(var32);
                     }
                  }

                  if (var24.size() == 0) {
                     if (var5) {
                        Object[] var41 = new Object[]{var9};
                     }

                     if (var9.getName().equals(A)) {
                        Object[] var42 = new Object[0];
                     }

                     var3.getNonIdentifiableRoutines().add(var9);
                  } else if (var24.size() == 1) {
                     Func var30 = (Func)var24.iterator().next();
                     var3.getIdentifiedRoutines().put(var9, var30);
                     var6.add(var9);
                  } else {
                     ArrayList var31 = new ArrayList();
                     var31.add(new Constraint(var24));
                     var7.put(var9, var31);
                  }
               }
            } else if (var5) {
               Object[] var44 = new Object[]{var9, var3.getIdentifiedRoutines().get(var9)};
            }
         }
      }

      var3.setRoutinesConstraints(var7);
      if (var5) {
         Object[] var45 = new Object[0];
      }

      return var6;
   }

   private boolean pC(Func var1) {
      return var1.getLowestAddress() >= this.oT && var1.getLowestAddress() < this.fI;
   }

   private void kS(List var1, Ws var2, Ws var3, MatchingState var4, boolean var5) {
      for (Func var7 : var1) {
         Func var8 = (Func)var4.getIdentifiedRoutines().get(var7);
         if (!var8.getModuleId().isUnknown()) {
            Set var9 = this.wS.get(var8);
            if (var9 != null) {
               Set var10 = var3.wS().get(var7);
               if (var10 != null) {
                  for (Func var12 : var10) {
                     if (!var12.isMapped()) {
                        if (var5) {
                           Object[] var20 = new Object[]{var12};
                        }
                     } else {
                        Constraint var13 = new Constraint(var9);
                        var4.addConstraint(var12, var13);
                     }
                  }
               } else if (var5) {
                  Object[] var19 = new Object[]{var7, var8};
               }
            } else if (var5 && var3.wS().get(var7) != null) {
               Object[] var10000 = new Object[]{var7, var8};
            }

            Set var15 = this.UT.get(var8);
            if (var15 != null) {
               Set var16 = var3.UT().get(var7);
               if (var16 != null) {
                  for (Func var18 : var16) {
                     Constraint var14 = new Constraint(var15);
                     var4.addConstraint(var18, var14);
                  }
               } else if (var5) {
                  Object[] var22 = new Object[]{var7, var8};
               }
            } else if (var5 && var3.UT().get(var7) != null) {
               Object[] var21 = new Object[]{var7, var8};
            }
         }
      }
   }

   private void wS(List var1, Ws var2, Ws var3, MatchingState var4, boolean var5) {
      for (Func var7 : var1) {
         Func var8 = (Func)var4.getIdentifiedRoutines().get(var7);
         if (!var8.getModuleId().isUnknown()) {
            Set var9 = this.E.get(var8);
            if (var9 != null) {
               Set var10 = var3.E().get(var7);
               if (var10 != null) {
                  for (Func var12 : var10) {
                     if (!var12.isMapped()) {
                        if (var5) {
                           Object[] var20 = new Object[]{var12};
                        }
                     } else {
                        Constraint var13 = new Constraint(var9);
                        var4.addConstraint(var12, var13);
                     }
                  }
               } else if (var5) {
                  Object[] var19 = new Object[]{var7, var8};
               }
            } else if (var5 && var3.E().get(var7) != null) {
               Object[] var10000 = new Object[]{var7, var8};
            }

            Set var15 = this.sY.get(var8);
            if (var15 != null) {
               Set var16 = var3.sY().get(var7);
               if (var16 != null) {
                  for (Func var18 : var16) {
                     Constraint var14 = new Constraint(var15);
                     var4.addConstraint(var18, var14);
                  }
               } else if (var5) {
                  Object[] var22 = new Object[]{var7, var8};
               }
            } else if (var5 && var3.sY().get(var7) != null) {
               Object[] var21 = new Object[]{var7, var8};
            }
         }
      }
   }

   private void pC(Ws var1, Ws var2, MatchingState var3, boolean var4) {
      Set var5 = var2.pC();

      for (Module var7 : var3.getTargetModulesMapping().values()) {
         long var8 = var7.getBegin();
         long var10 = var7.getEnd();
         if (var8 != var10) {
            for (Func var13 : var5) {
               if (!var3.getIdentifiedRoutines().containsKey(var13)
                  && var13.getLowestAddress() != null
                  && var13.getHighestAddress() != null
                  && var13.getLowestAddress() >= var8
                  && var13.getHighestAddress() <= var10) {
                  Set var14 = var1.ys().get(var7.getId());
                  Collection var15 = (Collection)var14.stream()
                     .filter(var1x -> !var3.getIdentifiedRoutines().values().contains(var1x))
                     .collect(Collectors.toSet());
                  var3.addConstraint(var13, new Constraint(var15));
               }
            }
         }
      }
   }

   private void A(Ws var1, Ws var2, MatchingState var3, boolean var4) {
      if (!var3.getTargetModulesMapping().isEmpty()) {
         long var5 = var3.getMatchedRangeStartAddress();
         long var7 = var3.getMatchedRangeEndAddress();
         Collection var9 = (Collection)var2.kS()
            .keySet()
            .stream()
            .filter(
               var6 -> !this.gp.contains(var6)
                  && (
                     var6.getLowestAddress() >= var5 && var6.getLowestAddress() <= var7 || var6.getHighestAddress() >= var5 && var6.getHighestAddress() <= var7
                  )
                  && !var3.getIdentifiedRoutines().containsKey(var6)
            )
            .collect(Collectors.toSet());
         if (var4) {
            Object[] var10000 = new Object[]{var9.size()};
         }

         for (Func var11 : var9) {
            for (rQ var14 : var2.kS().get(var11)) {
               if (var1.A().keySet().stream().filter(var1x -> var1x.equals(var14) && var1x.pC() == DH.kS).count() == 0L) {
                  Set var15 = this.ys.get(var14);
                  if (var15 != null) {
                     var3.addConstraint(var11, new Constraint(var15));
                  }
               }
            }
         }

         this.gp.addAll(var9);
      }
   }

   private void pC(BiMap var1) {
      for (Func var3 : var1.keySet()) {
         Object[] var10000 = new Object[]{var3, var1.get(var3)};
      }
   }

   private void pC(List var1) {
      for (Constraint var3 : var1) {
         Object[] var10000 = new Object[]{var3.getPossibleFuncs()};
      }
   }

   private void pC(MatchingState var1) {
      for (Entry var3 : var1.getRoutinesConstraints().entrySet()) {
         Func var4 = (Func)var3.getKey();
         if (!var4.getName().startsWith("sub_")) {
            for (Constraint var6 : (List)var3.getValue()) {
               boolean var7 = false;

               for (Func var9 : var6.getPossibleFuncs()) {
                  if (var9.getName().equals(var4.getName())) {
                     var7 = true;
                  }
               }

               if (!var7) {
                  Object[] var10000 = new Object[]{var4.getName()};
               }
            }
         }
      }
   }

   private boolean kS(Ws var1, Ws var2, MatchingState var3, boolean var4) {
      BiMap var5 = var3.getIdentifiedRoutines();

      for (rQ var7 : var2.A().keySet()) {
         if (var7 instanceof RC) {
            Set var8 = var2.A().get(var7);
            Set var9 = var1.A().get(var7);
            if (var9 == null || var9.isEmpty()) {
               var3.getNonIdentifiableRoutines().addAll(var8);
            }
         }
      }

      HashSet var13 = new HashSet();

      for (rQ var18 : var2.A().keySet()) {
         if (var1.A().keySet().stream().filter(var1x -> var1x.equals(var18) && var1x.pC() == DH.kS).count() != 0L) {
            Set var22 = var2.A().get(var18);
            Set var10 = this.ys.get(var18);
            if (var22.size() > var10.size()) {
               if (var4) {
                  Object[] var10000 = new Object[]{var18};
               }

               var13.add(var18);
            }
         }
      }

      for (rQ var19 : var2.A().keySet()) {
         if (!var13.contains(var19)) {
            Set var23 = var2.A().get(var19);
            if (var23.size() == 1) {
               Func var26 = (Func)var23.iterator().next();
               if (!var3.getNonIdentifiableRoutines().contains(var26)
                  && var1.A().keySet().stream().filter(var1x -> var1x.equals(var19) && var1x.pC() == DH.kS).count() != 0L) {
                  Set var11 = var1.A().get(var19);
                  if (var11 != null && var11.size() == 1) {
                     Func var12 = (Func)var11.iterator().next();
                     if (this.kS.contains(var12)) {
                        if (var4 && var5.containsKey(var26) && !((Func)var5.get(var26)).equals(var12)) {
                           Object[] var33 = new Object[]{var26, var5.get(var26), var12};
                        }

                        if (var26.getName().equals(A)) {
                           Object[] var34 = new Object[]{var12};
                        }

                        var5.put(var26, var12);
                     }
                  }
               }
            }
         }
      }

      if (var5.isEmpty()) {
         return false;
      } else {
         for (rQ var20 : var2.A().keySet()) {
            if (!var13.contains(var20) && var1.A().keySet().stream().filter(var1x -> var1x.equals(var20) && var1x.pC() == DH.kS).count() != 0L) {
               for (Func var29 : var2.A().get(var20)) {
                  if (!var5.containsKey(var29)) {
                     Set var31 = this.ys.get(var20);
                     if (var31 != null) {
                        var3.addConstraint(var29, new Constraint(var31));
                     }
                  }
               }
            }
         }

         if (var4) {
            for (rQ var21 : var1.A().keySet()) {
               Set var25 = var1.A().get(var21);
               if (var25 != null && var25.size() == 1) {
                  Func var28 = (Func)var25.iterator().next();
                  if (this.kS.contains(var28)) {
                     for (Func var32 : var2.ys().values()) {
                        if (var32.getName().equals(var28.getName()) && !var5.containsKey(var32)) {
                           Object[] var35 = new Object[]{var28, var32, var21};
                        }
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   private void pC(Ws var1, boolean var2) {
      for (Func var4 : var1.ys().values()) {
         Set var5 = var1.UT().get(var4);
         if (var5 == null) {
            this.kS.add(var4);
         } else {
            boolean var6 = true;
            Iterator var7 = var5.iterator();

            while (true) {
               if (var7.hasNext()) {
                  Func var8 = (Func)var7.next();
                  if (!var8.getModuleId().equals(var4.getModuleId())) {
                     continue;
                  }

                  var6 = false;
               }

               if (var6) {
                  this.kS.add(var4);
               }
               break;
            }
         }
      }

      if (var2) {
         Object[] var10000 = new Object[]{this.kS.size()};

         for (Func var59 : this.kS) {
            var10000 = new Object[0];
         }
      }

      for (Func var20 : var1.wS().keySet()) {
         HashSet var29 = new HashSet();
         ArrayList var36 = new ArrayList(var1.wS().get(var20));

         while (!var36.isEmpty()) {
            Func var42 = (Func)var36.remove(0);
            var29.add(var42);
            if (var42.getModuleId().equals(var20.getModuleId())) {
               Set var47 = var1.wS().get(var42);
               if (var47 != null) {
                  for (Func var10 : var47) {
                     if (!var36.contains(var10) && !var29.contains(var10)) {
                        var36.add(var10);
                     }
                  }
               }
            }
         }

         this.wS.putMulti(var20, var29);
      }

      if (var2) {
         Object[] var61 = new Object[0];

         for (Func var21 : this.wS.keySet()) {
            var61 = new Object[]{var21, this.wS.get(var21)};
         }
      }

      for (Func var22 : var1.UT().keySet()) {
         HashSet var30 = new HashSet();
         ArrayList var37 = new ArrayList(var1.UT().get(var22));

         while (!var37.isEmpty()) {
            Func var43 = (Func)var37.remove(0);
            var30.add(var43);
            Set var48 = var1.UT().get(var43);
            if (var48 != null) {
               for (Func var56 : var48) {
                  if (var56.getModuleId().equals(var43.getModuleId()) && !var37.contains(var56) && !var30.contains(var56)) {
                     var37.add(var56);
                  }
               }
            }
         }

         this.UT.putMulti(var22, var30);
      }

      if (var2) {
         Object[] var63 = new Object[0];

         for (Func var23 : this.UT.keySet()) {
            var63 = new Object[]{var23, this.UT.get(var23)};
         }
      }

      for (rQ var24 : var1.A().keySet()) {
         HashSet var31 = new HashSet();
         ArrayList var38 = new ArrayList(var1.A().get(var24));

         while (!var38.isEmpty()) {
            Func var44 = (Func)var38.remove(0);
            var31.add(var44);
            Set var49 = var1.UT().get(var44);
            if (var49 != null) {
               for (Func var57 : var49) {
                  if (var57.getModuleId().equals(var44.getModuleId()) && !var38.contains(var57) && !var31.contains(var57)) {
                     var38.add(var57);
                  }
               }
            }
         }

         this.ys.putMulti(var24, var31);
      }

      if (var2) {
         Object[] var65 = new Object[0];

         for (rQ var25 : this.ys.keySet()) {
            var65 = new Object[]{var25, this.ys.get(var25)};
         }
      }

      this.ld.addAll(var1.ld().values());
      SetMap var19 = var1.E();

      for (Func var32 : this.wS.keySet()) {
         Object var39 = var1.E().get(var32);
         if (var39 == null) {
            var39 = new HashSet();
         }

         for (Func var50 : this.wS.get(var32)) {
            if (var32.getModuleId().equals(var50.getModuleId())) {
               Set var54 = var19.get(var50);
               if (var54 != null) {
                  var39.addAll(var54);
               }
            }
         }

         if (!var39.isEmpty()) {
            this.E.putMulti(var32, (Collection)var39);
         }
      }

      if (var2) {
         Object[] var67 = new Object[0];

         for (Func var33 : this.E.keySet()) {
            var67 = new Object[]{var33, this.E.get(var33)};
         }
      }

      SetMap var28 = var1.sY();

      for (Func var40 : var28.keySet()) {
         HashSet var46 = new HashSet(var28.get(var40));

         for (Func var55 : var28.get(var40)) {
            Set var58 = this.UT.get(var55);
            if (var58 != null) {
               Set var11 = (Set)var58.stream().filter(var1x -> var1x.getModuleId().equals(var55.getModuleId())).collect(Collectors.toSet());
               if (!var11.isEmpty()) {
                  var46.addAll(var11);
               }
            }
         }

         this.sY.putMulti(var40, var46);
      }

      if (var2) {
         Object[] var69 = new Object[0];

         for (Func var41 : this.sY.keySet()) {
            var69 = new Object[]{var41, this.sY.get(var41)};
         }
      }
   }
}
