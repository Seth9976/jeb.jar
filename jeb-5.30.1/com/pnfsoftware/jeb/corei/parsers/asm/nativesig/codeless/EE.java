package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessLibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessLibraryVersion;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Constraint;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ExecutableModelMetadata;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.Func;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.MatchingState;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.ModuleId;
import com.pnfsoftware.jeb.core.units.code.asm.type.CannotImportTypeException;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Bu;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.aae;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.v;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class EE {
   private static final ILogger q = GlobalLog.getLogger(EE.class);
   private static final String RF = "unk";
   private static final String xK = "unk";
   private static final String Dw = "unk";
   private static final String Uv = "::";
   private static final int oW = 6;

   public void q(String var1, String var2) throws JebException, IOException {
      Assert.a(var1.endsWith(".jdb2"));
      File var3 = new File(var1);
      Assert.a(var3.exists(), "> wrong JDB2 path");
      MatchingState var4 = MatchingState.deserialize(var2);
      if (var4 == null) {
         q.error("> failed to open state");
      } else {
         this.q(var3, var4);
      }
   }

   public void q(File var1, MatchingState var2) throws JebException, IOException {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI var3 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI("x86", 1, 0, null, false);
      var3.q(0L);
      var3.q(true);
      HeadlessClientContext var4 = Bu.q(var3);

      try {
         List var5 = Bu.q(var4, var1.getParent(), var1.getName());
         if (var5.size() != 1) {
            Object[] var11 = new Object[0];
            return;
         }

         if (((IUnit)var5.get(0)).getChildren().size() == 0) {
            Object[] var10 = new Object[0];
            return;
         }

         List var6 = UnitUtil.findChildrenByType((IUnit)var5.get(0), INativeCodeUnit.class, false);
         if (var6.size() == 1) {
            this.q((INativeCodeUnit)var6.get(0), var2);
            return;
         }

         Object[] var10000 = new Object[0];
      } finally {
         var4.getEnginesContext().saveProject(((IRuntimeProject)var4.getEnginesContext().getProjects().get(0)).getKey());
         Bu.q(var4, var3);
      }
   }

   public void q(INativeCodeUnit var1, MatchingState var2) {
      this.q((abg)var1, var2);
   }

   public void q(abg var1, MatchingState var2) {
      if (!var2.getIdentifiedRoutines().isEmpty()) {
         NativeCommentManager var3 = var1.getCommentManager();
         IReferenceManager var4 = var1.getCodeModel().getReferenceManager();
         v var5 = (v)var1.getCodeModel().getLabelManager();
         String var6 = "unk";
         ExecutableModelMetadata var7 = var2.getRefMetadata();
         if (var7 != null) {
            var6 = var7.getLibraryId().getName();
            boolean var8 = var1.getPropertyManager().getBoolean("LoadTypeLibrariesCodelessSigs");
            if (var8) {
               CodelessLibraryID var9 = var7.getLibraryId();
               CodelessLibraryVersion var10 = var7.getLibraryVersion();
               TypeLibraryService var11 = var1.getTypeLibraryService();
               boolean var12 = false;

               for (TypeLibraryEntry var14 : var11.getAvailables()) {
                  if (var14.getTypelib() == null
                     && var14.getMetadataHeader() != null
                     && var14.getMetadataHeader().getDescription() != null
                     && var14.getMetadataHeader().getName().startsWith(var9.getTypelibName())
                     && (
                        var14.getMetadataHeader().getDescription().contains(var10.getStringVersion())
                           || var10.getStringVersion().contains(var14.getMetadataHeader().getDescription())
                     )
                     && var14.getMetadataHeader().getProcessorTypes().contains(var1.getProcessor().getType())) {
                     var11.load(var14);
                     q.info(S.L("automatically loaded typelib %s"), var14.getMetadataHeader().getName());
                     var12 = true;
                     break;
                  }
               }

               if (!var12) {
                  q.info(S.L("no suitable typelib could be automatically found, you might want to manually load one"));
               }
            }
         }

         for (Func var24 : var2.getIdentifiedRoutines().keySet()) {
            Func var25 = (Func)var2.getIdentifiedRoutines().get(var24);
            axp var27 = var1.RF(var24.getEntryPoint());
            if (var27 != null) {
               String var28 = var25.getModuleId().isUnknown() ? "unk::unk" : var6 + "::" + var25.getModuleId().getFileName();
               if (var5.q(var27.getName(true))) {
                  try {
                     ((aae)var1.getCodeAnalyzer()).q(var27, var25.getName(), false);
                  } catch (CannotImportTypeException var21) {
                  }

                  var27.setName(var28 + "::" + var25.getName());
               }

               var27.q(new CU(var25));
            }
         }

         long var23 = (Long)var2.getIdentifiedRoutinesRange().getFirst();
         long var26 = (Long)var2.getIdentifiedRoutinesRange().getSecond();

         for (Entry var30 : var2.getRoutinesConstraints().entrySet()) {
            axp var31 = var1.RF(((Func)var30.getKey()).getEntryPoint());
            if (var31 != null
               && ((Func)var30.getKey()).getHighestAddress() >= var23
               && ((Func)var30.getKey()).getHighestAddress() <= var26
               && ((Func)var30.getKey()).getLowestAddress() >= var23
               && ((Func)var30.getKey()).getLowestAddress() <= var26) {
               HashSet var15 = new HashSet();

               for (Constraint var17 : (List)var30.getValue()) {
                  var15.addAll(var17.getPossibleFuncs());
               }

               if (var5.q(var31.getName(true))) {
                  Set var32 = (Set)var15.stream().map(var0 -> var0.getModuleId()).collect(Collectors.toSet());
                  boolean var18 = true;
                  String var34;
                  if (var32.size() == 1) {
                     ModuleId var19 = (ModuleId)var32.iterator().next();
                     if (var19.isUnknown()) {
                        var34 = "unk::unk";
                        var18 = false;
                     } else {
                        var34 = var6 + "::" + var19.getFileName();
                     }
                  } else {
                     var34 = var6 + "::unk";
                  }

                  if (var18) {
                     var31.setName(var34 + "::unk");
                  }
               }

               Set var33 = (Set)var15.stream().map(var0 -> var0.getName()).limit(6L).collect(Collectors.toSet());
               String var35 = "[" + Strings.joinv(",", var33.toArray()) + (var15.size() > 6 ? ",..." : "") + "]";
               MetaComment var36 = new MetaComment(var35);

               for (IReference var20 : var4.getReferencesTo(var31.oW().getMemoryAddress())) {
                  var3.addMetaComment(var1.getSymbolicStringAddress(var20.getFrom().getInternalAddress()), var36, false);
               }

               var31.q(new CU(var15));
            }
         }
      }
   }
}
