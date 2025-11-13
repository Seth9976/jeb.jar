package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.DevPluginClassname;
import com.pnfsoftware.jeb.core.EnginesPropertiesUtil;
import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.ICoreContext;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IEnginesPlugin;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.core.IPluginManager;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.dao.IDataProvider;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseReader;
import com.pnfsoftware.jeb.core.dao.IFileDatabaseWriter;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.core.dao.impl.JDB2Reader;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.JebEventSource;
import com.pnfsoftware.jeb.core.events.QuestionNotificationPath;
import com.pnfsoftware.jeb.core.exceptions.DemoLimitationException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypePath;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnitPlugin;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureDBManager;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessSignatureManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeCodePseudoIdentifier;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeDecompilerPseudoIdentifier;
import com.pnfsoftware.jeb.util.base.Flags;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.concurrent.DaemonExecutors;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.io.FileMonitor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.net.NetProxyInfo;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationCancelledException;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@SerDisabled
public class zJ extends JebEventSource implements IEnginesContext {
   public static final ILogger Me = GlobalLog.getLogger(zJ.class);
   public static final String q = "DatabaseBackupBeforeSave";
   public static final String RF = "NetworkProxy";
   public static final String xK = "Lvt";
   public static final String Dw = "TypelibsFolder";
   public static final String Uv = "SiglibsFolder";
   public static final String oW = "CodelessSigsFolder";
   public static final String gO = "DevPluginClasspath";
   public static final String nf = "DevPluginClassnames";
   public static final String gP = "PluginsFolder";
   public static final String za = "LoadJavaScriptPlugins";
   public static final String lm = "LoadPythonPlugins";
   public static final String zz = "FlattenStringsInCodeDocuments";
   public static final String JY = "CustomPrintableCharacterRanges";
   public static final String HF = "CustomNotPrintableCharacterRanges";
   private static Thread PV;
   private Xa oQ;
   private Net xW;
   private IDataProvider KT;
   private ExecutorService Gf;
   private Map Ef;
   private Map cC;
   private IPropertyDefinitionManager sH;
   private IPropertyManager CE;
   private Pl wF;
   public Jh If;
   private TypeLibraryService Dz;
   private volatile boolean mI;
   private NativeSignatureDBManager jq;
   private volatile boolean ui;
   private CodelessSignatureManager TX;
   private volatile boolean Rr;
   public static final int LK = 1;
   public static final int io = 2;
   public static final int qa = 16;
   public Flags Hk = new Flags();

   zJ(Xa oq, IDataProvider kt, File file, boolean b, NetProxyInfo netProxyInfo) {
      if (oq != null && kt != null) {
         Object[] array = new Object[0];
         this.oQ = oq;
         this.KT = kt;
         this.Gf = DaemonExecutors.newFixedThreadPool(10);
         this.Ef = new LinkedHashMap();
         this.cC = new HashMap();
         this.sH = new PropertyDefinitionManager(null, null, S.L("Back-end properties for the engines, projects, and plugins"), 32);
         this.CE = new PropertyManager(this.sH, kt.getConfiguration());
         this.sH
            .addDefinition(
               "DatabaseBackupBeforeSave",
               PropertyTypeBoolean.create(true),
               S.L(
                  "Before saving a project to a JDB2 database, the current database is copied to the user's temporary folder and will be used as backup in case any problem happen during saving."
               ),
               8
            );
         this.sH
            .addDefinition(
               "NetworkProxy",
               PropertyTypeString.create(),
               S.L(
                  "Proxy settings for JEB back-end components. The format of this property is:\n'type|hostname|port|user|pass|whitelist'\n- type can be direct (no proxy), http, or socks\n- hostname and port are mandatory for non-direct proxy types\n- user and password are optional (needed only if your proxy requires authentication)\n- whitelist is an optional list of domains/IPs (with support for the wildcard character *) for which no proxy connection should be used (always direct connect)"
               ),
               8
            );
         this.sH.addInternalDefinition("Lvt", PropertyTypeInteger.create(0));
         this.xW = new Net();
         String string = this.CE.getString("NetworkProxy");
         if (!Strings.isBlank(string) && Net.getGlobalProxyInformation() == null) {
            Net.setGlobalProxyInformation(NetProxyInfo.parse(string));
         }

         String abs = "";
         String abs2 = "";
         String abs3 = "";
         String abs4 = "";
         IFileStore pluginStore = kt.getPluginStore();
         if (pluginStore != null && pluginStore.getStoreLocation() != null) {
            abs = com.pnfsoftware.jeb.util.io.IO.abs(pluginStore.getStoreLocation());
            abs2 = com.pnfsoftware.jeb.util.io.IO.abs(new File(abs, "../typelibs"));
            abs3 = com.pnfsoftware.jeb.util.io.IO.abs(new File(abs, "../siglibs"));
            abs4 = com.pnfsoftware.jeb.util.io.IO.abs(new File(abs3, "codeless"));
         }

         this.sH.addDefinition("TypelibsFolder", PropertyTypePath.create(abs2), S.L("Folder containing native type libraries"), 8);
         this.sH.addDefinition("SiglibsFolder", PropertyTypePath.create(abs3), S.L("Folder containing native code signature libraries"), 8);
         this.sH.addDefinition("CodelessSigsFolder", PropertyTypePath.create(abs4), S.L("Folder containing codeless signature libraries"), 8);
         this.sH.addDefinition("DevPluginClasspath", PropertyTypeString.create(""), S.L("Classpath for your in-development plugins (they are not JARs)"), 8);
         this.sH
            .addDefinition(
               "DevPluginClassnames", PropertyTypeString.create(""), S.L("Classnames of your in-development plugin entry-point classes (they are not JARs)"), 8
            );
         this.sH.addDefinition("PluginsFolder", PropertyTypeString.create(abs), S.L("Location of the JEB back-end plugins folder"), 8);
         this.sH
            .addDefinition(
               "LoadJavaScriptPlugins",
               PropertyTypeBoolean.create(true),
               S.L(
                  "Specify whether or not JEB back-end script plugins written in Java are allowed and should be loaded. When this option is enabled, Java script plugins located in the plugins folder 'scripts' subdirectory (typically, coreplugins/scripts) can be loaded and instantiated by back-end components."
               ),
               8
            );
         this.sH
            .addDefinition(
               "LoadPythonPlugins",
               PropertyTypeBoolean.create(false),
               S.L(
                  "Specify whether or not JEB back-end script plugins written in Python are allowed and should be loaded. When this option is enabled, Python script plugins located in the plugins folder 'scripts' subdirectory (typically, coreplugins/scripts) can be loaded and instantiated by back-end components."
               ),
               8
            );
         this.sH
            .addDefinition(
               "FlattenStringsInCodeDocuments",
               PropertyTypeBoolean.create(false),
               S.L(
                  "This global setting instructs code document generators (e.g., generating assembly or source code) to 'flatten' the strings before rendering. This means that all code tokens will be partially escaped as a means to ensure proper rendering of obfuscated names, regardless of the typeface or system in-use."
               ),
               8
            );
         this.sH
            .addDefinition(
               "CustomPrintableCharacterRanges",
               PropertyTypeString.create(),
               S.L(
                  "Ranges of characters that should be considered printable, i.e. not be escaped (customization of Formatter.isPrintableChar). Formatted as a CSL of UTF-16 chars and ranges: xxxx,xxxx-xxxx,xxxx-xxxx,..."
               )
            );
         this.sH
            .addDefinition(
               "CustomNotPrintableCharacterRanges",
               PropertyTypeString.create(),
               S.L(
                  "Ranges of characters that should be escaped, i.e. not considered printable (customization of Formatter.isPrintableChar). Formatted as a CSL of UTF-16 chars and ranges: xxxx,xxxx-xxxx,xxxx-xxxx,..."
               )
            );
         wq.q(this.CE, pluginStore == null ? null : pluginStore.getStoreLocation());
         CodeDocumentPart.enableTokenFlattening(this.CE.getBoolean("FlattenStringsInCodeDocuments"));
         this.q(this.CE, true);
         this.q(this.CE, false);
         File dw = this.Dw();
         this.If = new Jh(this, dw, file, b);
         if (!this.Hk.has(1)) {
            Jh.eo eo = new Jh.eo();
            String string2 = this.CE.getString("DevPluginClasspath");
            if (!string2.isEmpty()) {
               eo.q(string2);

               for (DevPluginClassname devPluginClassname : new EnginesPropertiesUtil(this.CE).getDevPluginClassnames()) {
                  if (devPluginClassname.isEnabled()) {
                     eo.RF(devPluginClassname.getClassname());
                  }
               }
            }

            if (dw.isDirectory()) {
               eo.RF(dw);
            }

            this.If.q(eo);
            if (this.CE.getBoolean("LoadJavaScriptPlugins")) {
               this.If.q();
            }

            if (this.CE.getBoolean("LoadPythonPlugins")) {
               this.If.RF();
            }
         }

         if (this.If != null) {
            this.If.q(cvg.class);
            this.If.q(bei.class);
            this.If.q(cvf.class);
            this.If.q(cvb.class);
            this.If.q(cuz.class);
            this.If.q(cux.class);

            for (IPluginFileEntry pluginFileEntry : this.If.getPluginEntries(IEnginesPlugin.class)) {
               if (pluginFileEntry.isValidPlugin()) {
                  IEnginesPlugin enginesPlugin = (IEnginesPlugin)pluginFileEntry.getPluginObject();
                  if (enginesPlugin != null) {
                     try {
                        enginesPlugin.load(this);
                     } catch (Exception var20) {
                        Me.error(S.L("%s: engines plugin load() call failed"), enginesPlugin.getPluginInformation().getName());
                        Me.catching(var20);
                     }
                  }
               }
            }
         }

         wq.q();
         (this.wF = new Pl((String)null)).q(this, this.sH, false);
         if (!this.Hk.has(2)) {
            cuu q = cuu.q(true);

            for (IUnitIdentifier unitIdentifier : this.Uv().getProcessor().getUnitIdentifiers()) {
               if (unitIdentifier.getTypeIdProvider() != null) {
                  q.addAll(unitIdentifier.getTypeIdProvider());
               }
            }
         }

         this.Dz = new TypeLibraryService();
         if (!this.Hk.has(2)) {
            String string3 = this.CE.getString("TypelibsFolder");
            if (!Strings.isBlank(string3)) {
               File file2 = new File(string3);
               if (file2.isDirectory()) {
                  this.Dz.addFolder(file2, false);
               }
            }
         }

         this.jq = NativeSignatureDBManager.getInstance(this);
         if (!this.Hk.has(2)) {
            String string4 = this.CE.getString("SiglibsFolder");
            if (!Strings.isBlank(string4)) {
               File file3 = new File(string4);
               if (file3.listFiles() != null) {
                  for (File file4 : file3.listFiles()) {
                     if (file4.isDirectory()) {
                        this.jq.addFolder(file4, false);
                     }
                  }
               }
            }
         }

         this.TX = CodelessSignatureManager.getInstance(this);
         if (!this.Hk.has(2)) {
            String string5 = this.CE.getString("CodelessSigsFolder");
            if (!Strings.isBlank(string5)) {
               File file5 = new File(string5);
               if (file5.isDirectory()) {
                  this.TX.addFolder(file5, false);
               }
            }
         }

         this.notifyListeners(new JebEvent(J.ContextInitialized, this));
         this.oW();
         this.addListener(new KZ(this));
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Xa q() {
      return this.oQ;
   }

   public Jh RF() {
      return this.If;
   }

   @Override
   public TypeLibraryService getTypeLibraryService() {
      if (!this.mI) {
         synchronized (this) {
            if (!this.mI) {
               this.Dz.rescan();
               this.mI = true;
            }
         }
      }

      return this.Dz;
   }

   @Override
   public NativeSignatureDBManager getNativeSignatureDBManager() {
      if (!this.ui) {
         synchronized (this) {
            if (!this.ui) {
               this.jq.rescan();
               this.ui = true;
            }
         }
      }

      return this.jq;
   }

   @Override
   public CodelessSignatureManager getCodelessSignatureManager() {
      if (!this.Rr) {
         synchronized (this) {
            if (!this.Rr) {
               this.TX.rescan();
               this.Rr = true;
            }
         }
      }

      return this.TX;
   }

   private void oW() {
      if (!this.Hk.has(16)) {
         if (PV != null) {
            return;
         }

         PV = ThreadUtil.start("jeb-connectivity-verifier", new s(this));
      }
   }

   public synchronized Net xK() {
      return new Net(this.xW);
   }

   public File Dw() {
      return new File(this.getPropertyManager().getString("PluginsFolder"));
   }

   public IRuntimeProject Uv() {
      return this.wF;
   }

   @Override
   public IDataProvider getDataProvider() {
      return this.KT;
   }

   @Override
   public ExecutorService getExecutorService() {
      return this.Gf;
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.CE;
   }

   @Override
   public String unitProperty(String s, String s2) {
      return UnitUtil.unitProperty(this, s, s2);
   }

   @Override
   public List getEnginesPlugins() {
      return this.If == null ? Collections.emptyList() : this.If.getPlugins(IEnginesPlugin.class);
   }

   @Override
   public List getUnitIdentifiers() {
      return this.Uv().getProcessor().getUnitIdentifiers();
   }

   @Override
   public IUnitIdentifier getUnitIdentifier(String s) {
      for (IUnitIdentifier unitIdentifier : this.getUnitIdentifiers()) {
         if (s.equals(unitIdentifier.getFormatType())) {
            return unitIdentifier;
         }
      }

      return null;
   }

   @Override
   public List getDebuggerUnitIdentifiers() {
      return this.Uv().getProcessor().getDebuggerUnitIdentifiers();
   }

   @Override
   public List getNativeDisassemblerPlugins() {
      ArrayList list = new ArrayList();

      for (IUnitIdentifier unitIdentifier : this.getUnitIdentifiers()) {
         if (unitIdentifier instanceof NativeCodePseudoIdentifier nativeCodePseudoIdentifier) {
            list.add(nativeCodePseudoIdentifier.getPlugin());
         }
      }

      return list;
   }

   @Override
   public List getNativeDecompilerPlugins() {
      ArrayList list = new ArrayList();

      for (IUnitIdentifier unitIdentifier : this.getUnitIdentifiers()) {
         if (unitIdentifier instanceof NativeDecompilerPseudoIdentifier nativeDecompilerPseudoIdentifier) {
            list.add(nativeDecompilerPseudoIdentifier.getPlugin());
         }
      }

      return list;
   }

   @Override
   public boolean setIdentifierEnabled(IUnitIdentifier unitIdentifier, boolean q) {
      return wq.q(unitIdentifier, q);
   }

   @Override
   public boolean isIdentifierEnabled(IUnitIdentifier unitIdentifier) {
      return wq.q(unitIdentifier);
   }

   public void q(Pl pl, String s) {
      String key = pl.getKey();
      this.Ef.put(key, pl);
      this.cC.put(key, s);
   }

   public boolean q(String s, boolean b) {
      if (!this.Ef.containsKey(s)) {
         return false;
      } else {
         this.Ef.remove(s);
         if (b) {
            this.cC.remove(s);
         }

         return true;
      }
   }

   @Override
   public void clearUnloadedProject() {
      Iterator iterator = this.cC.keySet().iterator();

      while (iterator.hasNext()) {
         if (!this.Ef.containsKey(iterator.next())) {
            iterator.remove();
         }
      }
   }

   public void RF(Pl pl, String s) {
      this.cC.put(pl.getKey(), s);
   }

   public String q(Pl pl) {
      String key = pl.getKey();
      String s = (String)this.cC.get(key);
      return s != null ? s : key;
   }

   @Override
   public IRuntimeProject loadProject(String s) throws IOException {
      return this.loadProject(s, false, null);
   }

   @Override
   public IRuntimeProject loadProject(String name, boolean b, IProgressCallback progressCallback) throws IOException {
      String s = name;
      String parent = null;
      File file = new File(name);
      if (file.isAbsolute()) {
         parent = file.getParent();
         name = file.getName();
      }

      Pl ex = (Pl)this.Ef.get(name);
      if (ex != null) {
         return ex;
      } else {
         IFileDatabaseReader databaseReader = null;

         try {
            if (this.cC.containsKey(name)) {
               s = (String)this.cC.get(name);
            }

            databaseReader = this.KT.getProjectDatabase().getDatabaseReader(s);
         } catch (IOException var103) {
         }

         boolean tc = false;
         boolean b2 = false;
         if (databaseReader != null) {
            FileMonitor create = null;

            try {
               String setCwd = null;
               if (databaseReader.hasBackingFile()) {
                  File backingFile = databaseReader.getBackingFile();
                  File parentFile = backingFile.getParentFile();
                  if (parentFile != null && parentFile.isDirectory()) {
                     setCwd = com.pnfsoftware.jeb.util.io.IO.setCwd(parentFile.getAbsolutePath());
                  }

                  create = FileMonitor.create(new File(backingFile.getPath() + ".lock"));
                  if (create.isLocked()) {
                     throw new IOException("The JDB2 database is locked");
                  }

                  create.lock();
               }

               if (databaseReader instanceof JDB2Reader && ((JDB2Reader)databaseReader).getJebVersionInt() == 0) {
                  tc = true;
               }

               try {
                  SerializationManager serializationManager = new SerializationManager(cuu.q());
                  if (this.If != null) {
                     serializationManager.addClassloader(this.If.getClassloader());
                  }

                  boolean b3 = databaseReader.getRecordDescription(5526081) != null;
                  boolean b4 = databaseReader.getRecordDescription(4870736) != null;
                  boolean b5 = databaseReader.getRecordDescription(4932433) != null;
                  boolean b6 = databaseReader.getRecordDescription(4411472) != null;
                  boolean b7 = databaseReader.getRecordDescription(5066832) != null;
                  IConfiguration jn = null;
                  if (b6) {
                     try (InputStream record = databaseReader.getRecord(4411472)) {
                        Deserializer deserializer = serializationManager.getDeserializer(record);
                        deserializer.initialize();
                        jn = (IConfiguration)deserializer.deserialize();
                     } catch (Exception var108) {
                        Me.error(S.L("Error reading configuration for persisted project"));
                     }
                  }

                  Zu dq = null;
                  if (b7) {
                     try (InputStream record2 = databaseReader.getRecord(5066832)) {
                        Deserializer deserializer2 = serializationManager.getDeserializer(record2);
                        deserializer2.initialize();
                        dq = (Zu)deserializer2.deserialize();
                     } catch (Exception var106) {
                        Me.error(S.L("Error reading metadata for persisted project"));
                     }
                  }

                  Throwable exception3 = null;
                  boolean b8 = false;
                  Deserializer deserializer3 = null;
                  if (b4) {
                     try (InputStream record3 = databaseReader.getRecord(4870736)) {
                        deserializer3 = serializationManager.getDeserializer(record3);
                        deserializer3.initialize();
                        boolean b9 = false;
                        if (dq != null) {
                           deserializer3.setExpectedObjectCount(dq.q());
                           if (progressCallback != null) {
                              deserializer3.addProgressCallback(progressCallback);
                              b9 = true;
                           }
                        }

                        long currentTimeMillis = System.currentTimeMillis();
                        ex = (Pl)deserializer3.deserialize();
                        long n = System.currentTimeMillis() - currentTimeMillis;
                        if (b9) {
                           deserializer3.removeProgressCallback(progressCallback);
                        }

                        int objectCount = deserializer3.getObjectCount();
                        ex.gO = objectCount;
                        Me.debug("Restored %d objects in %s", objectCount, TimeFormatter.formatTimestampDelta(n));
                        ex.xK = parent;
                        b8 = true;
                     } catch (DemoLimitationException var110) {
                        throw var110;
                     } catch (SerializationCancelledException var111) {
                        throw var111;
                     } catch (Exception var112) {
                        exception3 = var112;
                     }
                  }

                  if (!b8) {
                     if (exception3 != null) {
                        Me.catchingSilent(exception3);
                        Me.error(S.L("The JDB2 could not be properly reloaded; attempting fall-back restoration..."));
                     }

                     List tc2 = null;
                     if (b3) {
                        InputStream record4 = databaseReader.getRecord(databaseReader.getRecordDescription(5526081));

                        try {
                           tc2 = ((cut)serializationManager.getDeserializer(record4).deserialize()).q();
                        } catch (Exception var99) {
                           Me.catching(var99);
                        }
                     } else if (deserializer3 != null && deserializer3.isInitialized()) {
                        tc2 = new ArrayList();

                        for (Object next : deserializer3.getObjects()) {
                           if (next instanceof IArtifact) {
                              tc2.add((IArtifact)next);
                           }
                        }
                     }

                     if (tc2 != null) {
                        Iterator iterator2 = tc2.iterator();

                        while (iterator2.hasNext()) {
                           IInput input = ((IArtifact)iterator2.next()).getInput();
                           if (input.getCurrentSize() <= 0L) {
                              if (input instanceof FileInput fileInput) {
                                 String path = fileInput.getPath();
                                 QuestionNotificationPath questionNotificationPath = new QuestionNotificationPath(
                                    QuestionNotificationPath.Type.SELECT_FILE, S.L("An input artifact is missing. Please provide an alternate path."), path
                                 );
                                 this.q().notifyListeners(new JebEvent(J.Notification, questionNotificationPath));
                                 if (questionNotificationPath.getResponse() != null) {
                                    File file2 = new File((String)questionNotificationPath.getResponse());
                                    if (file2.canRead()) {
                                       fileInput.setFile(file2);
                                       continue;
                                    }
                                 }

                                 if (path != null && parent != null) {
                                    File file3 = new File(parent, com.pnfsoftware.jeb.util.io.IO.basename(path));
                                    if (file3.canRead()) {
                                       fileInput.setFile(file3);
                                       continue;
                                    }
                                 }
                              }

                              iterator2.remove();
                           }
                        }
                     }

                     if (tc2 == null || tc2.isEmpty()) {
                        Object[] array = new Object[0];
                        RuntimeException ex9 = new RuntimeException("The original artifacts are needed to reload this project");
                        if (exception3 != null) {
                           ex9.addSuppressed(exception3);
                        }

                        throw ex9;
                     }

                     List tc3 = null;
                     if (b5) {
                        InputStream record5 = databaseReader.getRecord(databaseReader.getRecordDescription(4932433));

                        try {
                           tc3 = ((cuv)serializationManager.getDeserializer(record5).deserialize()).q();
                        } catch (Exception var98) {
                           Me.catching(var98);
                        }
                     } else if (deserializer3 != null && deserializer3.isInitialized()) {
                        tc3 = new ArrayList();

                        for (Object next2 : deserializer3.getObjects()) {
                           if (next2 instanceof IUnit unit) {
                              try {
                                 IQuickStateObject generateQuickState = unit.generateQuickState();
                                 if (generateQuickState != null) {
                                    tc3.add(generateQuickState);
                                 }
                              } catch (Exception var97) {
                              }
                           }
                        }
                     }

                     ex = new Pl(name);
                     ex.setParentSource(this);
                     ex.xK = parent;
                     ex.Dw = jn;
                     ex.q(this, null, true);
                     this.q(ex, s);
                     b2 = true;
                     ArrayList list = new ArrayList();
                     ex.RF = true;

                     try {
                        Iterator iterator4 = tc2.iterator();

                        while (iterator4.hasNext()) {
                           list.add(ex.q((IArtifact)iterator4.next(), null, false, false, true, false));
                        }
                     } finally {
                        ex.RF = false;
                     }

                     if (tc3 != null && !tc3.isEmpty()) {
                        List allUnits = RuntimeProjectUtil.getAllUnits(ex);
                        int n2 = 0;
                        int i = 0;

                        label930:
                        while (!tc3.isEmpty() && !allUnits.isEmpty()) {
                           IQuickStateObject quickStateObject = (IQuickStateObject)tc3.remove(0);
                           boolean b10 = false;

                           for (int j = 0; j < allUnits.size(); j++) {
                              IUnit unit2 = (IUnit)allUnits.get(j);
                              if (quickStateObject.isTargetUnit(unit2)) {
                                 b10 = true;
                                 if (unit2.isProcessed()) {
                                    n2++;
                                    if (quickStateObject.reload(unit2)) {
                                       i++;
                                    }

                                    allUnits.remove(j);
                                    if (!b10) {
                                       ex.oW.add(quickStateObject);
                                    }
                                    continue label930;
                                 }

                                 ex.Uv.put(unit2, quickStateObject);
                              }
                           }
                           break;
                        }

                        if (n2 != i) {
                           Me.info(S.L("Partially restored units: %d success, %d fail"), i, n2 - i);
                        }
                     }

                     Iterator iterator5 = list.iterator();

                     while (iterator5.hasNext()) {
                        ex.q((ry)iterator5.next());
                     }
                  }
               } finally {
                  if (setCwd != null) {
                     com.pnfsoftware.jeb.util.io.IO.setCwd(setCwd);
                  }
               }
            } catch (Exception var114) {
               if (create != null) {
                  try {
                     create.unlock(true);
                  } catch (InterruptedException var96) {
                  }
               }

               throw var114;
            } finally {
               databaseReader.close();
            }
         } else {
            if (b) {
               throw new JebRuntimeException("The project does not exist: key=" + name);
            }

            ex = new Pl(name);
         }

         ex.xK = parent;
         if (!b2) {
            ex.setParentSource(this);
            ex.q(this, null, true);
            this.q(ex, s);
         }

         ex.q = tc;
         this.notifyListeners(new JebEvent(J.ProjectLoaded, ex));
         return ex;
      }
   }

   @Override
   public boolean unloadProject(String s) {
      return this.unloadProject(s, false);
   }

   @Override
   public boolean unloadProject(String s, boolean b) {
      Pl pl = (Pl)this.Ef.get(s);
      if (pl == null) {
         return false;
      } else {
         IFileDatabaseReader databaseReader = null;

         try {
            databaseReader = this.KT.getProjectDatabase().getDatabaseReader(this.q(pl));
            if (databaseReader.hasBackingFile()) {
               File file = new File(databaseReader.getBackingFile().getPath() + ".lock");
               FileMonitor value = FileMonitor.get(file);
               if (value != null && value.isLocked()) {
                  value.unlock(true);
                  file.delete();
               }
            }
         } catch (Exception var17) {
            if (databaseReader != null) {
               try {
                  databaseReader.close();
               } catch (IOException var16) {
               }
            }
         } finally {
            if (databaseReader != null) {
               try {
                  databaseReader.close();
               } catch (IOException var15) {
               }
            }
         }

         pl.close();
         this.Dz.unloadAll();
         boolean q = this.q(s, b);
         if (q) {
            this.notifyListeners(new JebEvent(J.ProjectUnloaded, pl));
         }

         return q;
      }
   }

   @Override
   public void unloadProjects() {
      this.unloadProjects(false);
   }

   @Override
   public void unloadProjects(boolean b) {
      Iterator iterator = new ArrayList(this.Ef.keySet()).iterator();

      while (iterator.hasNext()) {
         this.unloadProject((String)iterator.next(), b);
      }
   }

   private boolean gO() {
      try {
         return this.CE.getBoolean("DatabaseBackupBeforeSave");
      } catch (Exception var2) {
         return true;
      }
   }

   @Override
   public boolean saveProject(String s) throws IOException {
      return this.saveProject(s, null, null, null);
   }

   @Override
   public boolean saveProject(String s, String tc, Map map, IProgressCallback progressCallback) throws IOException {
      Pl ex = (Pl)this.Ef.get(s);
      if (ex == null) {
         return false;
      } else {
         boolean b = ex.getPersistenceStrategy() == 2;
         boolean b2 = true;
         boolean b3 = true;
         boolean b4 = !b;
         boolean b5 = b;
         boolean b6 = true;
         int n = 4;
         if (ex.q() && (map == null || !map.containsKey("DisableCompression"))) {
            n = n | 1 | 2;
         }

         if (tc == null) {
            tc = this.q(ex);
         } else {
            this.RF(ex, tc);
         }

         boolean b7 = false;
         File tempFile = null;
         if (this.gO()) {
            tempFile = File.createTempFile("jdb2-" + tc + "--", ".bak");
         }

         boolean var104;
         try {
            if (tempFile != null) {
               try (
                  InputStream fileReader = this.KT.getProjectDatabase().getFileReader(tc);
                  FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
               ) {
                  com.pnfsoftware.jeb.util.io.IO.copy(fileReader, fileOutputStream);
                  b7 = true;
               } catch (FileNotFoundException var84) {
               } catch (RuntimeException | IOException var85) {
                  Me.error(S.L("Could not copy current JDB2 to temporary backup file"));
               }
            }

            if (ex.q) {
               ex.q = false;

               try {
                  File fileObject = this.KT.getProjectDatabase().getFileObject(tc);
                  String[] splitExtension = com.pnfsoftware.jeb.util.io.IO.splitExtension(fileObject.getPath());
                  File file = new File(splitExtension[0] + ".backup-for-jeb3" + splitExtension[1]);
                  Me.warn(S.L("The original JDB2 was generated by JEB 3.x or below. It will be backed up to: %s"), file);

                  try (
                     FileInputStream fileInputStream = new FileInputStream(fileObject);
                     FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                  ) {
                     com.pnfsoftware.jeb.util.io.IO.copy(fileInputStream, fileOutputStream2);
                  }
               } catch (Exception var75) {
                  Me.catching(var75);
               }
            }

            IFileDatabaseWriter databaseWriter = null;

            try {
               databaseWriter = this.KT.getProjectDatabase().getDatabaseWriter(tc);
               if (databaseWriter.hasBackingFile()) {
                  File file2 = new File(databaseWriter.getBackingFile().getPath() + ".lock");
                  if (!file2.isFile()) {
                     FileMonitor create = FileMonitor.create(file2);
                     if (!create.isLocked()) {
                        create.lock();
                     }
                  }
               }

               cuu tc2 = cuu.q();
               ArrayList list = new ArrayList();
               Iterator iterator = ex.getLiveArtifacts().iterator();

               while (iterator.hasNext()) {
                  list.add(((ILiveArtifact)iterator.next()).getArtifact());
               }

               cut bxc = new cut(list);
               OutputStream beginRecord = databaseWriter.beginRecord(5526081, n);
               Serializer serializer = new SerializationManager(tc2).getSerializer(beginRecord);
               if (ex.getPropertyManager().getBoolean(".project.PersistArtifactFiles")) {
                  serializer.setCustomSerializationClassFlags(FileInput.class, 1);
               }

               serializer.serialize(bxc);
               databaseWriter.endRecord(beginRecord);
               list = new ArrayList();

               for (IUnit unit : RuntimeProjectUtil.getAllUnits(ex)) {
                  try {
                     IQuickStateObject generateQuickState = unit.generateQuickState();
                     if (generateQuickState != null) {
                        list.add(generateQuickState);
                     }
                  } catch (Exception var70) {
                     Me.error(S.L("%s: An error occurred when generating the unit's quick state"), UnitUtil.buildFullyQualifiedUnitPath(unit));
                     Me.catchingSilent(var70);
                  }
               }

               cuv bxe = new cuv(list);
               OutputStream beginRecord2 = databaseWriter.beginRecord(4932433, n);
               new SerializationManager(tc2).getSerializer(beginRecord2).serialize(bxe);
               databaseWriter.endRecord(beginRecord2);
               int objectCount = 0;
               if (b4) {
                  ex.xK();
                  OutputStream beginRecord3 = databaseWriter.beginRecord(4870736, n);
                  Serializer serializer2 = new SerializationManager(tc2).getSerializer(beginRecord3);
                  boolean b8 = false;
                  serializer2.setExpectedObjectCount(ex.gO);
                  if (progressCallback != null) {
                     serializer2.addProgressCallback(progressCallback);
                     b8 = true;
                  }

                  long currentTimeMillis = System.currentTimeMillis();
                  serializer2.serialize(ex);
                  long n2 = System.currentTimeMillis() - currentTimeMillis;
                  if (b8) {
                     serializer2.removeProgressCallback(progressCallback);
                  }

                  objectCount = serializer2.getObjectCount();
                  ex.gO = objectCount;
                  Me.debug("Serialized %d objects in %s", objectCount, TimeFormatter.formatTimestampDelta(n2));
                  databaseWriter.endRecord(beginRecord3);
                  serializer2.close();
               }

               if (b5 && ex.Dw != null) {
                  OutputStream beginRecord4 = databaseWriter.beginRecord(4411472, n);
                  new SerializationManager(tc2).getSerializer(beginRecord4).serialize(ex.Dw);
                  databaseWriter.endRecord(beginRecord4);
               }

               Zu dq = new Zu(objectCount);
               OutputStream beginRecord5 = databaseWriter.beginRecord(5066832, n);
               new SerializationManager(tc2).getSerializer(beginRecord5).serialize(dq);
               databaseWriter.endRecord(beginRecord5);
               this.notifyListeners(new JebEvent(J.ProjectSaved));
               databaseWriter.verify();
               var104 = true;
            } catch (IOException var81) {
               if (b7) {
                  try (
                     OutputStream fileWriter = this.KT.getProjectDatabase().getFileWriter(tc);
                     FileInputStream fileInputStream2 = new FileInputStream(tempFile);
                  ) {
                     com.pnfsoftware.jeb.util.io.IO.copy(fileInputStream2, fileWriter);
                  } catch (RuntimeException | IOException var80) {
                     Me.error(S.L("Could not restore JDB2 from temporary backup"));
                  }
               }

               throw var81;
            } finally {
               if (databaseWriter != null) {
                  databaseWriter.close();
               }
            }
         } finally {
            if (tempFile != null) {
               tempFile.delete();
            }
         }

         return var104;
      }
   }

   @Override
   public boolean hasProjects() {
      return !this.Ef.isEmpty();
   }

   @Override
   public IRuntimeProject getMainProject() {
      if (this.Ef.isEmpty()) {
         throw new IllegalStateException("The JEB engines context does not host any project!");
      } else {
         return (IRuntimeProject)this.Ef.values().iterator().next();
      }
   }

   public Pl q(String s) {
      return (Pl)this.Ef.get(s);
   }

   public Pl q(int n) {
      return (Pl)Lists.get(new ArrayList(this.Ef.values()), n);
   }

   @Override
   public List getProjects() {
      return new ArrayList(this.Ef.values());
   }

   @Override
   public File getProjectFile(String q) {
      Pl pl = (Pl)this.Ef.get(q);
      if (pl != null) {
         q = this.q(pl);
      }

      try {
         IFileDatabaseReader databaseReader = this.KT.getProjectDatabase().getDatabaseReader(q);
         if (databaseReader != null) {
            File var4;
            try {
               if (!databaseReader.hasBackingFile()) {
                  return null;
               }

               var4 = databaseReader.getBackingFile();
            } finally {
               databaseReader.close();
            }

            return var4;
         }
      } catch (IOException var9) {
      }

      return null;
   }

   @Override
   public void close() {
      this.unloadProjects();
      this.Ef = null;
      if (this.If != null) {
         Iterator iterator = this.If.q.iterator();

         while (iterator.hasNext()) {
            wq.RF((Class)iterator.next());
         }
      }

      this.Gf.shutdown();
      this.notifyListeners(new JebEvent(J.ContextClosed, this));
   }

   private void q(IPropertyManager propertyManager, boolean b) {
      String s = b ? "CustomPrintableCharacterRanges" : "CustomNotPrintableCharacterRanges";
      String trim = propertyManager.getString(s).trim();
      if (!trim.isEmpty()) {
         for (String s2 : trim.split(",")) {
            String[] split2 = s2.split("\\-");
            int n = -1;
            int n2 = -1;
            if (split2.length == 2) {
               String trim2 = split2[0].trim();
               String trim3 = split2[1].trim();
               if (trim2.length() == 4 && trim3.length() == 4) {
                  try {
                     n = Integer.parseInt(trim2, 16);
                     n2 = Integer.parseInt(trim3, 16) + 1;
                  } catch (Exception var15) {
                  }
               }
            } else if (split2.length == 1) {
               String trim4 = split2[0].trim();
               if (trim4.length() == 4) {
                  try {
                     n = Integer.parseInt(trim4, 16);
                     n2 = n + 1;
                  } catch (Exception var17) {
                  }
               }
            }

            if (n >= n2) {
               Me.error(S.L("Bad char or character range: %s (found in property: %s)"), s2, s);
            } else {
               try {
                  Formatter.addCustomPrintableCharRange(n, n2, b);
               } catch (Exception var16) {
                  Me.error(S.L("Error recording character range: %s (found in property: %s)"), s2, s);
               }
            }
         }
      }
   }

   @Override
   public IPluginManager getPluginManager() {
      return this.RF();
   }

   @Override
   public IRuntimeProject getProject(int n) {
      return this.q(n);
   }

   @Override
   public IRuntimeProject getProject(String s) {
      return this.q(s);
   }

   @Override
   public INet getNetworkUtility() {
      return this.xK();
   }

   @Override
   public ICoreContext getCoreContext() {
      return this.q();
   }
}
