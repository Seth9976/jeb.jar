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
import com.pnfsoftware.jeb.util.io.IO;
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
public class Nj extends JebEventSource implements IEnginesContext {
   public static final ILogger A = GlobalLog.getLogger(Nj.class);
   private static Thread kS;
   private GA wS;
   private Net UT;
   private IDataProvider E;
   private ExecutorService sY;
   private Map ys;
   private Map ld;
   private IPropertyDefinitionManager gp;
   private IPropertyManager oT;
   private Or fI;
   public MG WR;
   private TypeLibraryService NS;
   private volatile boolean vP;
   private NativeSignatureDBManager xC;
   private volatile boolean ED;
   private CodelessSignatureManager Sc;
   private volatile boolean ah;
   public Flags pC = new Flags();

   Nj(GA ws, IDataProvider e, File file, boolean b, NetProxyInfo netProxyInfo) {
      if (ws != null && e != null) {
         Object[] array = new Object[0];
         this.wS = ws;
         this.E = e;
         this.sY = DaemonExecutors.newFixedThreadPool(10);
         this.ys = new LinkedHashMap();
         this.ld = new HashMap();
         this.gp = new PropertyDefinitionManager(null, null, S.L("Back-end properties for the engines, projects, and plugins"), 32);
         this.oT = new PropertyManager(this.gp, e.getConfiguration());
         this.gp
            .addDefinition(
               "DatabaseBackupBeforeSave",
               PropertyTypeBoolean.create(true),
               S.L(
                  "Before saving a project to a JDB2 database, the current database is copied to the user's temporary folder and will be used as backup in case any problem happen during saving."
               ),
               8
            );
         this.gp
            .addDefinition(
               "NetworkProxy",
               PropertyTypeString.create(),
               S.L(
                  "Proxy settings for JEB back-end components. The format of this property is:\n'type|hostname|port|user|pass|whitelist'\n- type can be direct (no proxy), http, or socks\n- hostname and port are mandatory for non-direct proxy types\n- user and password are optional (needed only if your proxy requires authentication)\n- whitelist is an optional list of domains/IPs (with support for the wildcard character *) for which no proxy connection should be used (always direct connect)"
               ),
               8
            );
         this.gp.addInternalDefinition("Lvt", PropertyTypeInteger.create(0));
         this.UT = new Net();
         String string = this.oT.getString("NetworkProxy");
         if (!Strings.isBlank(string) && Net.getGlobalProxyInformation() == null) {
            Net.setGlobalProxyInformation(NetProxyInfo.parse(string));
         }

         String abs = "";
         String abs2 = "";
         String abs3 = "";
         String abs4 = "";
         IFileStore pluginStore = e.getPluginStore();
         if (pluginStore != null && pluginStore.getStoreLocation() != null) {
            abs = IO.abs(pluginStore.getStoreLocation());
            abs2 = IO.abs(new File(abs, "../typelibs"));
            abs3 = IO.abs(new File(abs, "../siglibs"));
            abs4 = IO.abs(new File(abs3, "codeless"));
         }

         this.gp.addDefinition("TypelibsFolder", PropertyTypePath.create(abs2), S.L("Folder containing native type libraries"), 8);
         this.gp.addDefinition("SiglibsFolder", PropertyTypePath.create(abs3), S.L("Folder containing native code signature libraries"), 8);
         this.gp.addDefinition("CodelessSigsFolder", PropertyTypePath.create(abs4), S.L("Folder containing codeless signature libraries"), 8);
         this.gp.addDefinition("DevPluginClasspath", PropertyTypeString.create(""), S.L("Classpath for your in-development plugins (they are not JARs)"), 8);
         this.gp
            .addDefinition(
               "DevPluginClassnames", PropertyTypeString.create(""), S.L("Classnames of your in-development plugin entry-point classes (they are not JARs)"), 8
            );
         this.gp.addDefinition("PluginsFolder", PropertyTypeString.create(abs), S.L("Location of the JEB back-end plugins folder"), 8);
         this.gp
            .addDefinition(
               "LoadJavaScriptPlugins",
               PropertyTypeBoolean.create(true),
               S.L(
                  "Specify whether or not JEB back-end script plugins written in Java are allowed and should be loaded. When this option is enabled, Java script plugins located in the plugins folder 'scripts' subdirectory (typically, coreplugins/scripts) can be loaded and instantiated by back-end components."
               ),
               8
            );
         this.gp
            .addDefinition(
               "LoadPythonPlugins",
               PropertyTypeBoolean.create(false),
               S.L(
                  "Specify whether or not JEB back-end script plugins written in Python are allowed and should be loaded. When this option is enabled, Python script plugins located in the plugins folder 'scripts' subdirectory (typically, coreplugins/scripts) can be loaded and instantiated by back-end components."
               ),
               8
            );
         this.gp
            .addDefinition(
               "FlattenStringsInCodeDocuments",
               PropertyTypeBoolean.create(false),
               S.L(
                  "This global setting instructs code document generators (e.g., generating assembly or source code) to 'flatten' the strings before rendering. This means that all code tokens will be partially escaped as a means to ensure proper rendering of obfuscated names, regardless of the typeface or system in-use."
               ),
               8
            );
         this.gp
            .addDefinition(
               "CustomPrintableCharacterRanges",
               PropertyTypeString.create(),
               S.L(
                  "Ranges of characters that should be considered printable, i.e. not be escaped (customization of Formatter.isPrintableChar). Formatted as a CSL of UTF-16 chars and ranges: xxxx,xxxx-xxxx,xxxx-xxxx,..."
               )
            );
         this.gp
            .addDefinition(
               "CustomNotPrintableCharacterRanges",
               PropertyTypeString.create(),
               S.L(
                  "Ranges of characters that should be escaped, i.e. not considered printable (customization of Formatter.isPrintableChar). Formatted as a CSL of UTF-16 chars and ranges: xxxx,xxxx-xxxx,xxxx-xxxx,..."
               )
            );
         XR.pC(this.oT, pluginStore == null ? null : pluginStore.getStoreLocation());
         CodeDocumentPart.enableTokenFlattening(this.oT.getBoolean("FlattenStringsInCodeDocuments"));
         this.pC(this.oT, true);
         this.pC(this.oT, false);
         File ws2 = this.wS();
         this.WR = new MG(this, ws2, file, b);
         if (!this.pC.has(1)) {
            MG.Av av = new MG.Av();
            String string2 = this.oT.getString("DevPluginClasspath");
            if (!string2.isEmpty()) {
               av.pC(string2);

               for (DevPluginClassname devPluginClassname : new EnginesPropertiesUtil(this.oT).getDevPluginClassnames()) {
                  if (devPluginClassname.isEnabled()) {
                     av.A(devPluginClassname.getClassname());
                  }
               }
            }

            if (ws2.isDirectory()) {
               av.A(ws2);
            }

            this.WR.pC(av);
            if (this.oT.getBoolean("LoadJavaScriptPlugins")) {
               this.WR.pC();
            }

            if (this.oT.getBoolean("LoadPythonPlugins")) {
               this.WR.A();
            }
         }

         if (this.WR != null) {
            this.WR.pC(ckr.class);
            this.WR.pC(bap.class);
            this.WR.pC(ckq.class);
            this.WR.pC(ckn.class);
            this.WR.pC(ckl.class);
            this.WR.pC(ckj.class);

            for (IPluginFileEntry pluginFileEntry : this.WR.getPluginEntries(IEnginesPlugin.class)) {
               if (pluginFileEntry.isValidPlugin()) {
                  IEnginesPlugin enginesPlugin = (IEnginesPlugin)pluginFileEntry.getPluginObject();
                  if (enginesPlugin != null) {
                     try {
                        enginesPlugin.load(this);
                     } catch (Exception var20) {
                        A.error(S.L("%s: engines plugin load() call failed"), enginesPlugin.getPluginInformation().getName());
                        A.catching(var20);
                     }
                  }
               }
            }
         }

         XR.pC();
         (this.fI = new Or((String)null)).pC(this, this.gp, false);
         if (!this.pC.has(2)) {
            ckh pc = ckh.pC(true);

            for (IUnitIdentifier unitIdentifier : this.UT().getProcessor().getUnitIdentifiers()) {
               if (unitIdentifier.getTypeIdProvider() != null) {
                  pc.addAll(unitIdentifier.getTypeIdProvider());
               }
            }
         }

         this.NS = new TypeLibraryService();
         if (!this.pC.has(2)) {
            String string3 = this.oT.getString("TypelibsFolder");
            if (!Strings.isBlank(string3)) {
               File file2 = new File(string3);
               if (file2.isDirectory()) {
                  this.NS.addFolder(file2, false);
               }
            }
         }

         this.xC = NativeSignatureDBManager.getInstance(this);
         if (!this.pC.has(2)) {
            String string4 = this.oT.getString("SiglibsFolder");
            if (!Strings.isBlank(string4)) {
               File file3 = new File(string4);
               if (file3.listFiles() != null) {
                  for (File file4 : file3.listFiles()) {
                     if (file4.isDirectory()) {
                        this.xC.addFolder(file4, false);
                     }
                  }
               }
            }
         }

         this.Sc = CodelessSignatureManager.getInstance(this);
         if (!this.pC.has(2)) {
            String string5 = this.oT.getString("CodelessSigsFolder");
            if (!Strings.isBlank(string5)) {
               File file5 = new File(string5);
               if (file5.isDirectory()) {
                  this.Sc.addFolder(file5, false);
               }
            }
         }

         this.notifyListeners(new JebEvent(J.ContextInitialized, this));
         this.E();
         this.addListener(new OW(this));
      } else {
         throw new IllegalArgumentException();
      }
   }

   public GA pC() {
      return this.wS;
   }

   public MG A() {
      return this.WR;
   }

   @Override
   public TypeLibraryService getTypeLibraryService() {
      if (!this.vP) {
         synchronized (this) {
            if (!this.vP) {
               this.NS.rescan();
               this.vP = true;
            }
         }
      }

      return this.NS;
   }

   @Override
   public NativeSignatureDBManager getNativeSignatureDBManager() {
      if (!this.ED) {
         synchronized (this) {
            if (!this.ED) {
               this.xC.rescan();
               this.ED = true;
            }
         }
      }

      return this.xC;
   }

   @Override
   public CodelessSignatureManager getCodelessSignatureManager() {
      if (!this.ah) {
         synchronized (this) {
            if (!this.ah) {
               this.Sc.rescan();
               this.ah = true;
            }
         }
      }

      return this.Sc;
   }

   private void E() {
      if (!this.pC.has(16)) {
         if (kS != null) {
            return;
         }

         kS = ThreadUtil.start("jeb-connectivity-verifier", new UH(this));
      }
   }

   public synchronized Net kS() {
      return new Net(this.UT);
   }

   public File wS() {
      return new File(this.getPropertyManager().getString("PluginsFolder"));
   }

   public IRuntimeProject UT() {
      return this.fI;
   }

   @Override
   public IDataProvider getDataProvider() {
      return this.E;
   }

   @Override
   public ExecutorService getExecutorService() {
      return this.sY;
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.oT;
   }

   @Override
   public String unitProperty(String s, String s2) {
      return UnitUtil.unitProperty(this, s, s2);
   }

   @Override
   public List getEnginesPlugins() {
      return this.WR == null ? Collections.emptyList() : this.WR.getPlugins(IEnginesPlugin.class);
   }

   @Override
   public List getUnitIdentifiers() {
      return this.UT().getProcessor().getUnitIdentifiers();
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
      return this.UT().getProcessor().getDebuggerUnitIdentifiers();
   }

   @Override
   public List getNativeDisassemblerPlugins() {
      ArrayList list = new ArrayList();

      for (IUnitIdentifier unitIdentifier : this.getUnitIdentifiers()) {
         if (unitIdentifier instanceof NativeCodePseudoIdentifier nativeCodePseudoIdentifier) {
            list.add(nativeCodePseudoIdentifier.pC());
         }
      }

      return list;
   }

   @Override
   public List getNativeDecompilerPlugins() {
      ArrayList list = new ArrayList();

      for (IUnitIdentifier unitIdentifier : this.getUnitIdentifiers()) {
         if (unitIdentifier instanceof NativeDecompilerPseudoIdentifier nativeDecompilerPseudoIdentifier) {
            list.add(nativeDecompilerPseudoIdentifier.pC());
         }
      }

      return list;
   }

   @Override
   public boolean setIdentifierEnabled(IUnitIdentifier unitIdentifier, boolean pc) {
      return XR.pC(unitIdentifier, pc);
   }

   @Override
   public boolean isIdentifierEnabled(IUnitIdentifier unitIdentifier) {
      return XR.pC(unitIdentifier);
   }

   public void pC(Or or, String s) {
      String key = or.getKey();
      this.ys.put(key, or);
      this.ld.put(key, s);
   }

   public boolean pC(String s, boolean b) {
      if (!this.ys.containsKey(s)) {
         return false;
      } else {
         this.ys.remove(s);
         if (b) {
            this.ld.remove(s);
         }

         return true;
      }
   }

   @Override
   public void clearUnloadedProject() {
      Iterator iterator = this.ld.keySet().iterator();

      while (iterator.hasNext()) {
         if (!this.ys.containsKey(iterator.next())) {
            iterator.remove();
         }
      }
   }

   public void gy(Or gh, String s) {
      this.ld.put(gh.getKey(), s);
   }

   public String pC(Or or) {
      String key = or.getKey();
      String s = (String)this.ld.get(key);
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

      Or gh = (Or)this.ys.get(name);
      if (gh != null) {
         return gh;
      } else {
         IFileDatabaseReader databaseReader = null;

         try {
            if (this.ld.containsKey(name)) {
               s = (String)this.ld.get(name);
            }

            databaseReader = this.E.getProjectDatabase().getDatabaseReader(s);
         } catch (IOException var103) {
         }

         boolean x = false;
         boolean b2 = false;
         if (databaseReader != null) {
            FileMonitor create = null;

            try {
               String setCwd = null;
               if (databaseReader.hasBackingFile()) {
                  File backingFile = databaseReader.getBackingFile();
                  File parentFile = backingFile.getParentFile();
                  if (parentFile != null && parentFile.isDirectory()) {
                     setCwd = IO.setCwd(parentFile.getAbsolutePath());
                  }

                  create = FileMonitor.create(new File(backingFile.getPath() + ".lock"));
                  if (create.isLocked()) {
                     throw new IOException("The JDB2 database is locked");
                  }

                  create.lock();
               }

               if (databaseReader instanceof JDB2Reader && ((JDB2Reader)databaseReader).getJebVersionInt() == 0) {
                  x = true;
               }

               try {
                  SerializationManager serializationManager = new SerializationManager(ckh.pC());
                  if (this.WR != null) {
                     serializationManager.addClassloader(this.WR.getClassloader());
                  }

                  boolean b3 = databaseReader.getRecordDescription(5526081) != null;
                  boolean b4 = databaseReader.getRecordDescription(4870736) != null;
                  boolean b5 = databaseReader.getRecordDescription(4932433) != null;
                  boolean b6 = databaseReader.getRecordDescription(4411472) != null;
                  boolean b7 = databaseReader.getRecordDescription(5066832) != null;
                  IConfiguration rd = null;
                  if (b6) {
                     try (InputStream record = databaseReader.getRecord(4411472)) {
                        Deserializer deserializer = serializationManager.getDeserializer(record);
                        deserializer.initialize();
                        rd = (IConfiguration)deserializer.deserialize();
                     } catch (Exception var108) {
                        A.error(S.L("Error reading configuration for persisted project"));
                     }
                  }

                  vh rw = null;
                  if (b7) {
                     try (InputStream record2 = databaseReader.getRecord(5066832)) {
                        Deserializer deserializer2 = serializationManager.getDeserializer(record2);
                        deserializer2.initialize();
                        rw = (vh)deserializer2.deserialize();
                     } catch (Exception var106) {
                        A.error(S.L("Error reading metadata for persisted project"));
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
                        if (rw != null) {
                           deserializer3.setExpectedObjectCount(rw.x());
                           if (progressCallback != null) {
                              deserializer3.addProgressCallback(progressCallback);
                              b9 = true;
                           }
                        }

                        long currentTimeMillis = System.currentTimeMillis();
                        gh = (Or)deserializer3.deserialize();
                        long n = System.currentTimeMillis() - currentTimeMillis;
                        if (b9) {
                           deserializer3.removeProgressCallback(progressCallback);
                        }

                        int objectCount = deserializer3.getObjectCount();
                        gh.sY = objectCount;
                        A.debug("Restored %d objects in %s", objectCount, TimeFormatter.formatTimestampDelta(n));
                        gh.kS = parent;
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
                        A.catchingSilent(exception3);
                        A.error(S.L("The JDB2 could not be properly reloaded; attempting fall-back restoration..."));
                     }

                     List x2 = null;
                     if (b3) {
                        InputStream record4 = databaseReader.getRecord(databaseReader.getRecordDescription(5526081));

                        try {
                           x2 = ((ckg)serializationManager.getDeserializer(record4).deserialize()).x();
                        } catch (Exception var99) {
                           A.catching(var99);
                        }
                     } else if (deserializer3 != null && deserializer3.isInitialized()) {
                        x2 = new ArrayList();

                        for (Object next : deserializer3.getObjects()) {
                           if (next instanceof IArtifact) {
                              x2.add((IArtifact)next);
                           }
                        }
                     }

                     if (x2 != null) {
                        Iterator iterator2 = x2.iterator();

                        while (iterator2.hasNext()) {
                           IInput input = ((IArtifact)iterator2.next()).getInput();
                           if (input.getCurrentSize() <= 0L) {
                              if (input instanceof FileInput fileInput) {
                                 String path = fileInput.getPath();
                                 QuestionNotificationPath questionNotificationPath = new QuestionNotificationPath(
                                    QuestionNotificationPath.Type.SELECT_FILE, S.L("An input artifact is missing. Please provide an alternate path."), path
                                 );
                                 this.pC().notifyListeners(new JebEvent(J.Notification, questionNotificationPath));
                                 if (questionNotificationPath.getResponse() != null) {
                                    File file2 = new File((String)questionNotificationPath.getResponse());
                                    if (file2.canRead()) {
                                       fileInput.setFile(file2);
                                       continue;
                                    }
                                 }

                                 if (path != null && parent != null) {
                                    File file3 = new File(parent, IO.basename(path));
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

                     if (x2 == null || x2.isEmpty()) {
                        Object[] array = new Object[0];
                        RuntimeException ex8 = new RuntimeException("The original artifacts are needed to reload this project");
                        if (exception3 != null) {
                           ex8.addSuppressed(exception3);
                        }

                        throw ex8;
                     }

                     List x3 = null;
                     if (b5) {
                        InputStream record5 = databaseReader.getRecord(databaseReader.getRecordDescription(4932433));

                        try {
                           x3 = ((cki)serializationManager.getDeserializer(record5).deserialize()).x();
                        } catch (Exception var98) {
                           A.catching(var98);
                        }
                     } else if (deserializer3 != null && deserializer3.isInitialized()) {
                        x3 = new ArrayList();

                        for (Object next2 : deserializer3.getObjects()) {
                           if (next2 instanceof IUnit unit) {
                              try {
                                 IQuickStateObject generateQuickState = unit.generateQuickState();
                                 if (generateQuickState != null) {
                                    x3.add(generateQuickState);
                                 }
                              } catch (Exception var97) {
                              }
                           }
                        }
                     }

                     gh = new Or(name);
                     gh.setParentSource(this);
                     gh.kS = parent;
                     gh.wS = rd;
                     gh.pC(this, null, true);
                     this.pC(gh, s);
                     b2 = true;
                     ArrayList list = new ArrayList();
                     gh.A = true;

                     try {
                        Iterator iterator4 = x2.iterator();

                        while (iterator4.hasNext()) {
                           list.add(gh.pC((IArtifact)iterator4.next(), null, false, false, true, false));
                        }
                     } finally {
                        gh.A = false;
                     }

                     if (x3 != null && !x3.isEmpty()) {
                        List allUnits = RuntimeProjectUtil.getAllUnits(gh);
                        int n2 = 0;
                        int i = 0;

                        label940:
                        while (!x3.isEmpty() && !allUnits.isEmpty()) {
                           IQuickStateObject quickStateObject = (IQuickStateObject)x3.remove(0);
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
                                       gh.E.add(quickStateObject);
                                    }
                                    continue label940;
                                 }

                                 gh.UT.put(unit2, quickStateObject);
                              }
                           }
                           break;
                        }

                        if (n2 != i) {
                           A.info(S.L("Partially restored units: %d success, %d fail"), i, n2 - i);
                        }
                     }

                     Iterator iterator5 = list.iterator();

                     while (iterator5.hasNext()) {
                        gh.pC((rm)iterator5.next());
                     }
                  }
               } finally {
                  if (setCwd != null) {
                     IO.setCwd(setCwd);
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

            if (!this.wS.kS().kS()) {
               throw new JebRuntimeException("Currently, your license does not permit the creation of new projects");
            }

            gh = new Or(name);
         }

         gh.kS = parent;
         if (!b2) {
            gh.setParentSource(this);
            gh.pC(this, null, true);
            this.pC(gh, s);
         }

         gh.pC = x;
         this.notifyListeners(new JebEvent(J.ProjectLoaded, gh));
         return gh;
      }
   }

   @Override
   public boolean unloadProject(String s) {
      return this.unloadProject(s, false);
   }

   @Override
   public boolean unloadProject(String s, boolean b) {
      Or or = (Or)this.ys.get(s);
      if (or == null) {
         return false;
      } else {
         IFileDatabaseReader databaseReader = null;

         try {
            databaseReader = this.E.getProjectDatabase().getDatabaseReader(this.pC(or));
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

         or.close();
         this.NS.unloadAll();
         boolean pc = this.pC(s, b);
         if (pc) {
            this.notifyListeners(new JebEvent(J.ProjectUnloaded, or));
         }

         return pc;
      }
   }

   @Override
   public void unloadProjects() {
      this.unloadProjects(false);
   }

   @Override
   public void unloadProjects(boolean b) {
      Iterator iterator = new ArrayList(this.ys.keySet()).iterator();

      while (iterator.hasNext()) {
         this.unloadProject((String)iterator.next(), b);
      }
   }

   @Override
   public boolean saveProject(String s) throws IOException {
      return this.saveProject(s, null, null, null);
   }

   @Override
   public boolean saveProject(String s, String x, Map map, IProgressCallback progressCallback) throws IOException {
      Or gh = (Or)this.ys.get(s);
      if (gh == null) {
         return false;
      } else {
         boolean b = gh.getPersistenceStrategy() == 2;
         boolean b2 = true;
         boolean b3 = true;
         boolean b4 = !b;
         boolean b5 = b;
         boolean b6 = true;
         int n = 4;
         if (gh.x() && (map == null || !map.containsKey("DisableCompression"))) {
            n = n | 1 | 2;
         }

         if (x == null) {
            x = this.pC(gh);
         } else {
            this.gy(gh, x);
         }

         boolean b7 = false;
         File tempFile = null;
         if (this.fA()) {
            tempFile = File.createTempFile("jdb2-" + x + "--", ".bak");
         }

         boolean var104;
         try {
            if (tempFile != null) {
               try (
                  InputStream fileReader = this.E.getProjectDatabase().getFileReader(x);
                  FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
               ) {
                  IO.copy(fileReader, fileOutputStream);
                  b7 = true;
               } catch (FileNotFoundException var84) {
               } catch (RuntimeException | IOException var85) {
                  A.error(S.L("Could not copy current JDB2 to temporary backup file"));
               }
            }

            if (gh.pC) {
               gh.pC = false;

               try {
                  File fileObject = this.E.getProjectDatabase().getFileObject(x);
                  String[] splitExtension = IO.splitExtension(fileObject.getPath());
                  File file = new File(splitExtension[0] + ".backup-for-jeb3" + splitExtension[1]);
                  A.warn(S.L("The original JDB2 was generated by JEB 3.x or below. It will be backed up to: %s"), file);

                  try (
                     FileInputStream fileInputStream = new FileInputStream(fileObject);
                     FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                  ) {
                     IO.copy(fileInputStream, fileOutputStream2);
                  }
               } catch (Exception var75) {
                  A.catching(var75);
               }
            }

            IFileDatabaseWriter databaseWriter = null;

            try {
               databaseWriter = this.E.getProjectDatabase().getDatabaseWriter(x);
               if (databaseWriter.hasBackingFile()) {
                  File file2 = new File(databaseWriter.getBackingFile().getPath() + ".lock");
                  if (!file2.isFile()) {
                     FileMonitor create = FileMonitor.create(file2);
                     if (!create.isLocked()) {
                        create.lock();
                     }
                  }
               }

               ckh x2 = ckh.pC();
               ArrayList list = new ArrayList();
               Iterator iterator = gh.getLiveArtifacts().iterator();

               while (iterator.hasNext()) {
                  list.add(((ILiveArtifact)iterator.next()).getArtifact());
               }

               ckg bni = new ckg(list);
               OutputStream beginRecord = databaseWriter.beginRecord(5526081, n);
               Serializer serializer = new SerializationManager(x2).getSerializer(beginRecord);
               if (gh.getPropertyManager().getBoolean(".project.PersistArtifactFiles")) {
                  serializer.setCustomSerializationClassFlags(FileInput.class, 1);
               }

               serializer.serialize(bni);
               databaseWriter.endRecord(beginRecord);
               list = new ArrayList();

               for (IUnit unit : RuntimeProjectUtil.getAllUnits(gh)) {
                  try {
                     IQuickStateObject generateQuickState = unit.generateQuickState();
                     if (generateQuickState != null) {
                        list.add(generateQuickState);
                     }
                  } catch (Exception var70) {
                     A.error(S.L("%s: An error occurred when generating the unit's quick state"), UnitUtil.buildFullyQualifiedUnitPath(unit));
                     A.catchingSilent(var70);
                  }
               }

               cki bnk = new cki(list);
               OutputStream beginRecord2 = databaseWriter.beginRecord(4932433, n);
               new SerializationManager(x2).getSerializer(beginRecord2).serialize(bnk);
               databaseWriter.endRecord(beginRecord2);
               int objectCount = 0;
               if (b4) {
                  gh.hw();
                  OutputStream beginRecord3 = databaseWriter.beginRecord(4870736, n);
                  Serializer serializer2 = new SerializationManager(x2).getSerializer(beginRecord3);
                  boolean b8 = false;
                  serializer2.setExpectedObjectCount(gh.sY);
                  if (progressCallback != null) {
                     serializer2.addProgressCallback(progressCallback);
                     b8 = true;
                  }

                  long currentTimeMillis = System.currentTimeMillis();
                  serializer2.serialize(gh);
                  long n2 = System.currentTimeMillis() - currentTimeMillis;
                  if (b8) {
                     serializer2.removeProgressCallback(progressCallback);
                  }

                  objectCount = serializer2.getObjectCount();
                  gh.sY = objectCount;
                  A.debug("Serialized %d objects in %s", objectCount, TimeFormatter.formatTimestampDelta(n2));
                  databaseWriter.endRecord(beginRecord3);
                  serializer2.close();
               }

               if (b5 && gh.wS != null) {
                  OutputStream beginRecord4 = databaseWriter.beginRecord(4411472, n);
                  new SerializationManager(x2).getSerializer(beginRecord4).serialize(gh.wS);
                  databaseWriter.endRecord(beginRecord4);
               }

               vh rw = new vh(objectCount);
               OutputStream beginRecord5 = databaseWriter.beginRecord(5066832, n);
               new SerializationManager(x2).getSerializer(beginRecord5).serialize(rw);
               databaseWriter.endRecord(beginRecord5);
               this.notifyListeners(new JebEvent(J.ProjectSaved));
               databaseWriter.verify();
               var104 = true;
            } catch (IOException var81) {
               if (b7) {
                  try (
                     OutputStream fileWriter = this.E.getProjectDatabase().getFileWriter(x);
                     FileInputStream fileInputStream2 = new FileInputStream(tempFile);
                  ) {
                     IO.copy(fileInputStream2, fileWriter);
                  } catch (RuntimeException | IOException var80) {
                     A.error(S.L("Could not restore JDB2 from temporary backup"));
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
      return !this.ys.isEmpty();
   }

   @Override
   public IRuntimeProject getMainProject() {
      if (this.ys.isEmpty()) {
         throw new IllegalStateException("The JEB engines context does not host any project!");
      } else {
         return (IRuntimeProject)this.ys.values().iterator().next();
      }
   }

   public Or pC(String s) {
      return (Or)this.ys.get(s);
   }

   private boolean fA() {
      try {
         return this.oT.getBoolean("DatabaseBackupBeforeSave");
      } catch (Exception var2) {
         return true;
      }
   }

   public Or pC(int n) {
      return (Or)Lists.get(new ArrayList(this.ys.values()), n);
   }

   @Override
   public List getProjects() {
      return new ArrayList(this.ys.values());
   }

   @Override
   public File getProjectFile(String pc) {
      Or or = (Or)this.ys.get(pc);
      if (or != null) {
         pc = this.pC(or);
      }

      try {
         IFileDatabaseReader databaseReader = this.E.getProjectDatabase().getDatabaseReader(pc);
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
      this.ys = null;
      if (this.WR != null) {
         Iterator iterator = this.WR.pC.iterator();

         while (iterator.hasNext()) {
            XR.A((Class)iterator.next());
         }
      }

      this.sY.shutdown();
      this.notifyListeners(new JebEvent(J.ContextClosed, this));
   }

   private void pC(IPropertyManager propertyManager, boolean b) {
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
               A.error(S.L("Bad char or character range: %s (found in property: %s)"), s2, s);
            } else {
               try {
                  Formatter.addCustomPrintableCharRange(n, n2, b);
               } catch (Exception var16) {
                  A.error(S.L("Error recording character range: %s (found in property: %s)"), s2, s);
               }
            }
         }
      }
   }

   @Override
   public IPluginManager getPluginManager() {
      return this.A();
   }

   @Override
   public IRuntimeProject getProject(int n) {
      return this.pC(n);
   }

   @Override
   public IRuntimeProject getProject(String s) {
      return this.pC(s);
   }

   @Override
   public INet getNetworkUtility() {
      return this.kS();
   }

   @Override
   public ICoreContext getCoreContext() {
      return this.pC();
   }
}
