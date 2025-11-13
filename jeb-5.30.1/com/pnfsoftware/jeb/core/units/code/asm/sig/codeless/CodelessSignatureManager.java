package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.EnginesContextUtil;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.EE;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.eo;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.qV;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.SetMap;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodelessSignatureManager implements IEventListener {
   private static final ILogger logger = GlobalLog.getLogger(CodelessSignatureManager.class);
   public static final String CODELESS_SIGS_EXTENSION = ".codeless-siglib";
   public static final String CODELESS_FOLDER_DEFAULT_NAME = "codeless";
   private static CodelessSignatureManager instance;
   private final IEnginesContext enginesContext;
   private Set folders = new HashSet();
   private List entries = new ArrayList();
   private SetMap matchedEntries = new SetMap();

   public static CodelessSignatureManager getInstance(IEnginesContext var0) {
      if (instance == null) {
         instance = new CodelessSignatureManager(var0);
      }

      return instance;
   }

   private CodelessSignatureManager(IEnginesContext var1) {
      this.enginesContext = var1;
   }

   public void addFolder(File var1, boolean var2) {
      this.folders.add(var1);
      if (var2) {
         this.scan();
      }
   }

   public List getAvailablePackages() {
      return this.entries;
   }

   public void rescan() {
      this.scan();
   }

   public List scan() {
      for (File var2 : this.folders) {
         if (var2 != null && var2.exists() && var2.isDirectory()) {
            for (File var6 : var2.listFiles()) {
               if (var6.isFile() && var6.getName().endsWith(".codeless-siglib")) {
                  CodelessSignaturePackageEntry var7 = new CodelessSignaturePackageEntry(var6);
                  this.entries.add(var7);
               }
            }
         } else {
            logger.warn(S.L("Codeless signature folder is invalid: %s"), var2);
         }
      }

      return this.entries;
   }

   public boolean isMatched(INativeCodeUnit var1, CodelessSignaturePackageEntry var2) {
      return CollectionUtil.contains(this.matchedEntries.get(var1), var2);
   }

   public INativeCodeUnit getMatchableUnit() {
      IRuntimeProject var1 = EnginesContextUtil.getMainProject(this.enginesContext);
      return var1 == null ? null : (INativeCodeUnit)var1.findUnit(INativeCodeUnit.class);
   }

   public MatchingState match(INativeCodeUnit var1, CodelessSignaturePackageEntry var2) {
      qV var3 = new qV();
      MatchingState var4 = var3.q(var2.getFile().getAbsolutePath(), var1);
      if (var4 != null) {
         this.matchedEntries.put(var1, var2);
         var1.addListener(this);
      }

      return var4;
   }

   public boolean importState(INativeCodeUnit var1, MatchingState var2) {
      EE var3 = new EE();
      var3.q(var1, var2);
      return true;
   }

   public LibraryIdentificationResults identifyLibraries(INativeCodeUnit var1) {
      eo var2 = new eo();
      return var2.q(this.entries, var1);
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitDisposed && var2.getSource() instanceof INativeCodeUnit) {
         this.matchedEntries.remove((INativeCodeUnit)var2.getSource());
      }
   }
}
