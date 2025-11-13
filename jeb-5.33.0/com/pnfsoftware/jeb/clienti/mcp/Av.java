package com.pnfsoftware.jeb.clienti.mcp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.client.mcp.IJebMcpServer;
import com.pnfsoftware.jeb.client.mcp.JsonUtil;
import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionXrefsData;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.IUnitDocumentPresentation;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.TextPartUtil;
import com.pnfsoftware.jeb.core.units.ICertificateUnit;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.ICodeClass;
import com.pnfsoftware.jeb.core.units.code.ICodeField;
import com.pnfsoftware.jeb.core.units.code.ICodeMethod;
import com.pnfsoftware.jeb.core.units.code.ICodeString;
import com.pnfsoftware.jeb.core.units.code.ICodeType;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.DexBulkItemRenamer;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPackage;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexString;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.impl.ContainerUnit;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.corei.parsers.apk.Ws;
import com.pnfsoftware.jeb.util.encoding.HashCalculator;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.B;
import com.pnfsoftware.jebglobal.BP;
import com.pnfsoftware.jebglobal.GK;
import com.pnfsoftware.jebglobal.HE;
import com.pnfsoftware.jebglobal.Hv;
import com.pnfsoftware.jebglobal.KD;
import com.pnfsoftware.jebglobal.Kr;
import com.pnfsoftware.jebglobal.Mh;
import com.pnfsoftware.jebglobal.Pj;
import com.pnfsoftware.jebglobal.Pt;
import com.pnfsoftware.jebglobal.RC;
import com.pnfsoftware.jebglobal.Sb;
import com.pnfsoftware.jebglobal.Sf;
import com.pnfsoftware.jebglobal.Tb;
import com.pnfsoftware.jebglobal.Yd;
import com.pnfsoftware.jebglobal.co;
import com.pnfsoftware.jebglobal.dE;
import com.pnfsoftware.jebglobal.eW;
import com.pnfsoftware.jebglobal.gJ;
import com.pnfsoftware.jebglobal.gb;
import com.pnfsoftware.jebglobal.io;
import com.pnfsoftware.jebglobal.jM;
import com.pnfsoftware.jebglobal.lX;
import com.pnfsoftware.jebglobal.m;
import com.pnfsoftware.jebglobal.ma;
import com.pnfsoftware.jebglobal.nA;
import com.pnfsoftware.jebglobal.oP;
import com.pnfsoftware.jebglobal.p;
import com.pnfsoftware.jebglobal.qt;
import com.pnfsoftware.jebglobal.sy;
import com.pnfsoftware.jebglobal.t;
import com.pnfsoftware.jebglobal.uX;
import com.pnfsoftware.jebglobal.vi;
import com.pnfsoftware.jebglobal.yt;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.McpServerFeatures.SyncToolSpecification;
import io.modelcontextprotocol.server.transport.HttpServletSseServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ServerCapabilities;
import io.modelcontextprotocol.spec.McpSchema.Tool;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class Av implements IJebMcpServer {
   private static final ILogger pC = GlobalLog.getLogger(com.pnfsoftware.jeb.clienti.mcp.Av.class);
   private int A;
   private String kS;
   private IEnginesContext wS;
   private McpSyncServer UT;
   private Server E;
   private ObjectMapper sY;
   private SchemaGenerator ys;
   private String ld = "{\n    \"type\": \"object\",\n    \"properties\": {},\n    \"required\": []\n}\n";
   private static final AndroidPlatformABI[] gp = new AndroidPlatformABI[]{AndroidPlatformABI.ARM64, AndroidPlatformABI.X64, AndroidPlatformABI.ARM};

   public Av(int var1, String var2, IEnginesContext var3) throws Exception {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      HttpServletSseServerTransportProvider var4 = HttpServletSseServerTransportProvider.builder()
         .messageEndpoint("/")
         .sseEndpoint(var2)
         .objectMapper(new ObjectMapper())
         .build();
      this.sY = new ObjectMapper();
      JacksonModule var5 = new JacksonModule(new JacksonOption[]{JacksonOption.RESPECT_JSONPROPERTY_REQUIRED});
      SchemaGeneratorConfigBuilder var6 = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09)
         .with(var5)
         .without(Option.EXTRA_OPEN_API_FORMAT_VALUES, new Option[0])
         .with(Option.DEFINITIONS_FOR_ALL_OBJECTS, new Option[0])
         .without(Option.DEFINITIONS_FOR_ALL_OBJECTS, new Option[0])
         .without(Option.DEFINITIONS_FOR_MEMBER_SUPERTYPES, new Option[0]);
      var6.forFields().withNullableCheck(var0 -> false);
      SchemaGeneratorConfig var7 = var6.build();
      this.ys = new SchemaGenerator(var7);
      ArrayList var8 = new ArrayList();

      for (Method var12 : this.getClass().getDeclaredMethods()) {
         com.pnfsoftware.jeb.clienti.mcp.Av.Av var13 = (com.pnfsoftware.jeb.clienti.mcp.Av.Av)var12.getAnnotation(com.pnfsoftware.jeb.clienti.mcp.Av.Av.class);
         if (var13 != null && !var13.pC()) {
            SyncToolSpecification var14 = (SyncToolSpecification)var12.invoke(this);
            var8.add(var14);
         }
      }

      this.UT = McpServer.sync(var4)
         .serverInfo("jeb-mcp-server", "1.0.0")
         .capabilities(ServerCapabilities.builder().tools(true).prompts(true).resources(true, true).logging().build())
         .tools((SyncToolSpecification[])var8.toArray(new SyncToolSpecification[var8.size()]))
         .build();
      QueuedThreadPool var15 = new QueuedThreadPool();
      var15.setName("mcp-server");
      this.E = new Server(var15);
      ServerConnector var16 = new ServerConnector(this.E);
      var16.setPort(var1);
      this.E.addConnector(var16);
      ServletContextHandler var17 = new ServletContextHandler();
      var17.setContextPath("/");
      var17.addServlet(new ServletHolder(var4), "/*");
      this.E.setHandler(var17);
      this.E.start();
      Object[] var10000 = new Object[0];
   }

   @Override
   public int getPort() {
      return this.A;
   }

   @Override
   public String getEndpoint() {
      return this.kS;
   }

   @Override
   public String getName() {
      return "jeb-mcp-server";
   }

   @Override
   public String getVersion() {
      return "1.0.0";
   }

   @Override
   public void serve() {
      try {
         try {
            this.E.join();
         } catch (InterruptedException var10) {
            Object[] var10000 = new Object[0];
         } finally {
            try {
               this.UT.close();
               this.E.stop();
            } catch (Exception var9) {
            }
         }
      } catch (Exception var12) {
         pC.error("Failed to start MCP server: %s", var12.getMessage());
      }
   }

   private String pC(Type var1) {
      ObjectNode var2 = this.ys.generateSchema(var1, new Type[0]);

      try {
         return this.sY.writerWithDefaultPrettyPrinter().writeValueAsString(var2);
      } catch (JsonProcessingException var4) {
         throw new RuntimeException(var4);
      }
   }

   private String pC(Object var1) {
      try {
         return this.sY.writerWithDefaultPrettyPrinter().writeValueAsString(var1);
      } catch (JsonProcessingException var3) {
         throw new RuntimeException(var3);
      }
   }

   private CallToolResult pC(KD var1) {
      return CallToolResult.builder().structuredContent(this.pC((Object)var1)).build();
   }

   private CallToolResult A(String var1) {
      return CallToolResult.builder().addTextContent(var1).build();
   }

   private CallToolResult pC(KD var1, String var2) {
      var1.pC = true;
      var1.A = var2;
      return CallToolResult.builder().structuredContent(this.pC((Object)var1)).isError(true).build();
   }

   private CallToolResult kS(String var1) {
      return CallToolResult.builder().addTextContent(var1).isError(true).build();
   }

   IRuntimeProject pC() {
      return !this.wS.hasProjects() ? null : this.wS.getMainProject();
   }

   String pC(IUnit var1) {
      return UnitUtil.buildFullyQualifiedUnitPath(var1, true, "/");
   }

   IUnit pC(IRuntimeProject var1, String var2, Class var3) {
      if (!Strings.isBlank(var2)) {
         List var4 = Arrays.asList(var2.split("/"));
         List var5 = UnitUtil.getUnitsFromPathList(var1, var4);
         if (var5 != null && var5.size() == 1 && var3.isInstance(var5.get(0))) {
            return (IUnit)var5.get(0);
         }
      }

      return null;
   }

   uX.Sv A(IUnit var1) {
      return new uX.Sv(var1.getFormatType(), this.pC(var1));
   }

   boolean kS(IUnit var1) {
      if (!var1.isProcessed()) {
         if (!var1.process()) {
            return false;
         }

         if (!var1.isProcessed()) {
            return false;
         }
      }

      return true;
   }

   CallToolResult pC(ICodeUnit var1, KD var2) {
      if (var1 instanceof IDexUnit) {
         for (ICodeClass var4 : var1.getClasses()) {
            String var5 = var4.getSignature();
            if (var5.length() > 500) {
               String var6 = Strings.ff(
                  S.L(
                     "Some class signatures in this unit are too long! The names may be obfuscated to impede reverse-engineering efforts. Run the %s tool to auto-rename all dex items in bulk, then try again."
                  ),
                  "bulk_rename_dex_items"
               );
               if (var2 != null) {
                  return this.pC(var2, var6);
               }

               return this.kS(var6);
            }
         }
      }

      return null;
   }

   String pC(String var1) {
      if (var1 == null) {
         return var1;
      } else {
         var1 = var1.strip();
         if (!var1.startsWith("L")) {
            var1 = "L" + var1;
         }

         if (!var1.endsWith(";")) {
            var1 = var1 + ";";
         }

         return var1.replace('.', '/');
      }
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification A() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("get_project_information")
               .title(S.L("Get project information"))
               .description(
                  S.L("Retrieve high-level information about the project, such as its name, creation time, top-level artifact files, and units to work on.")
               )
               .inputSchema(this.ld)
               .outputSchema(this.pC((Type)uX.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            uX var3 = new uX();
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.pC(var3, S.L("There is no opened project. First, the user should open an existing project or create a new one."));
            } else {
               var3.kS = Strings.safe(var4.getName());
               var3.wS = TimeFormatter.formatTimestampLocal(var4.getCreationTimestamp());

               for (ILiveArtifact var6 : var4.getLiveArtifacts()) {
                  IArtifact var7 = var6.getArtifact();
                  String var8 = var7.getName();
                  long var9 = var7.getInput().getCurrentSize();
                  String var11 = "";

                  try (InputStream var12 = var7.getInput().getStream()) {
                     byte[] var13 = HashCalculator.sha256(var12);
                     if (var13 != null) {
                        var11 = Formatter.byteArrayToHex(var13).toString().toLowerCase();
                     }
                  } catch (IOException var24) {
                  }

                  var3.UT.add(new uX.Av(var8, var9, var11));
                  IUnit var25 = var6.getMainUnit();
                  if (var25 != null) {
                     if (!(var25 instanceof IApkUnit var26)) {
                        if (var25 instanceof IDexUnit var14) {
                           var3.E.add(this.A(var14));
                        } else if (var25 instanceof ICodeObjectUnit var15) {
                           INativeCodeUnit var27 = (INativeCodeUnit)UnitUtil.findFirstChildByType(var15, INativeCodeUnit.class, false);
                           if (var27 != null) {
                              var3.E.add(this.A(var27));
                           }

                           for (ICodeObjectUnit var29 : UnitUtil.findDescendantsByType(var15, ICodeObjectUnit.class, false)) {
                              INativeCodeUnit var31 = (INativeCodeUnit)UnitUtil.findFirstChildByType(var29, INativeCodeUnit.class, false);
                              if (var31 != null) {
                                 var3.E.add(this.A(var27));
                              }
                           }
                        }
                     } else {
                        var3.E.add(this.A(var26));
                        IDexUnit var16 = var26.getDex();
                        if (var16 != null) {
                           var3.E.add(this.A(var16));
                        }

                        if (var26.getLibraries() != null) {
                           List var18 = null;

                           for (int var19 = 0; var19 < gp.length; var19++) {
                              AndroidPlatformABI var17 = gp[var19];
                              var18 = var26.getLibrariesForArch(var17);
                              if (var18 != null) {
                                 break;
                              }
                           }

                           if (var18 != null) {
                              for (IELFUnit var20 : var18) {
                                 IUnit var21 = var20.getImageUnit();
                                 var3.E.add(this.A(var21));
                              }
                           }
                        }
                     }
                  }
               }

               return this.pC((KD)var3);
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification kS() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_units")
               .title(S.L("List units"))
               .description(
                  Strings.ff(
                     S.L(
                        "Retrieve all the units of this project, including those that were not provided by the high-level tool %s. The result is a list of unit paths."
                     ),
                     "get_project_information"
                  )
               )
               .inputSchema(this.pC((Type)Hv.class))
               .outputSchema(this.pC((Type)Sf.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            Hv var3 = (Hv)JsonUtil.mapToPojo(var2.arguments(), Hv.class);
            Sf var4 = new Sf();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project. First, the user should open an existing project or create a new one."));
            } else {
               List var6;
               if (!Strings.isBlank(var3.wS)) {
                  IUnit var7 = this.pC(var5, var3.wS, IUnit.class);
                  if (var7 == null) {
                     return this.pC(var4, S.L("The parent unit was not found"));
                  }

                  var6 = UnitUtil.findDescendants(var7, -1, null, null, false);
               } else {
                  var6 = RuntimeProjectUtil.getAllUnits(var5);
               }

               String var14 = var3.kS;
               if (var14 == null || var14.isEmpty() || var14.equals("*")) {
                  var14 = null;
               }

               int var8 = Math.max(var3.A, 100);
               int var9 = 0;
               int var10 = 0;

               for (IUnit var12 : var6) {
                  if (var9 >= var8) {
                     break;
                  }

                  if (!(var12 instanceof ContainerUnit)) {
                     if (var14 == null) {
                        if (var10 >= var3.pC) {
                           var4.kS.add(this.A(var12));
                           var9++;
                        }

                        var10++;
                     } else {
                        String var13 = this.pC(var12);
                        if (Strings.starMatches(var13, var14)) {
                           if (var10 >= var3.pC) {
                              var4.kS.add(this.A(var12));
                              var9++;
                           }

                           var10++;
                        }
                     }
                  }
               }

               return this.pC((KD)var4);
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification wS() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("get_apk_manifest")
               .title(S.L("Get APK Manifest"))
               .description(S.L("Retrieve the XML content of the Android APK manifest"))
               .inputSchema(this.pC((Type)p.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            p var3 = (p)JsonUtil.mapToPojo(var2.arguments(), p.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IApkUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IApkUnit)this.pC(var4, var3.pC, IApkUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The APK unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IApkUnit)var4.findUnit(Ws.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no APK unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The APK cannot be initialized, it cannot be used"));
               } else {
                  IXmlUnit var6 = var5.getManifest();
                  if (var6 == null) {
                     return this.kS(S.L("There is no Android Manifest in the APK unit"));
                  } else {
                     String var7 = var6.getDocumentAsText();
                     return var7 == null ? this.kS(S.L("There was a problem rendering the Android Manifest as XML data")) : this.A(var7);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification UT() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("get_apk_certificate")
               .title(S.L("Get APK certificate"))
               .description(S.L("Retrieve a human-readable summary of the X.509 certificate that was used for signing"))
               .inputSchema(this.pC((Type)vi.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            vi var3 = (vi)JsonUtil.mapToPojo(var2.arguments(), vi.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IApkUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IApkUnit)this.pC(var4, var3.pC, IApkUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The APK unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IApkUnit)var4.findUnit(Ws.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no APK unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The APK cannot be initialized, it cannot be used"));
               } else {
                  ICertificateUnit var6 = var5.getCertificate();
                  if (var6 == null) {
                     return this.kS(S.L("The APK does not appear to be signed"));
                  } else {
                     String var7 = var6.getCertificateAsText();
                     return var7 == null ? this.kS(S.L("There was a problem geenrating the certificate information")) : this.A(var7);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification E() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_apk_resources")
               .title(S.L("List the resources in the APK"))
               .description(S.L("List the fully-qualified paths of layouts, strings, images, and other structured resources stored in the APK"))
               .inputSchema(this.pC((Type)gJ.class))
               .outputSchema(this.pC((Type)Pj.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            gJ var3 = (gJ)JsonUtil.mapToPojo(var2.arguments(), gJ.class);
            Pj var4 = new Pj();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               IApkUnit var6;
               if (!Strings.isBlank(var3.pC)) {
                  var6 = (IApkUnit)this.pC(var5, var3.pC, IApkUnit.class);
                  if (var6 == null) {
                     return this.pC(var4, S.L("The APK unit with the provided path was not found in the project"));
                  }
               } else {
                  var6 = (IApkUnit)var5.findUnit(Ws.class);
                  if (var6 == null) {
                     return this.pC(var4, S.L("There is no APK unit in this project"));
                  }
               }

               if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The APK cannot be initialized, it cannot be used"));
               } else {
                  IUnit var7 = var6.getResources();
                  if (var7 == null) {
                     return this.pC(var4, S.L("The APK does not contain resources"));
                  } else {
                     String var8 = var3.wS;
                     if (var8 == null || var8.isEmpty() || var8.equals("*")) {
                        var8 = null;
                     }

                     int var9 = Math.max(var3.kS, 100);
                     int var10 = 0;
                     int var11 = 0;

                     for (IUnit var13 : var7.getChildren()) {
                        if (var13 instanceof ContainerUnit) {
                           for (IUnit var15 : var13.getChildren()) {
                              if (var10 >= var9) {
                                 return this.pC((KD)var4);
                              }

                              if (var8 == null) {
                                 if (var11 >= var3.A) {
                                    String var16 = var13.getName() + "/" + var15.getName();
                                    var4.kS.add(var16);
                                    var10++;
                                 }

                                 var11++;
                              } else {
                                 String var17 = var13.getName() + "/" + var15.getName();
                                 if (Strings.starMatches(var17, var8)) {
                                    if (var11 >= var3.A) {
                                       var4.kS.add(var17);
                                       var10++;
                                    }

                                    var11++;
                                 }
                              }
                           }
                        }
                     }

                     return this.pC((KD)var4);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification sY() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("get_apk_resource_by_path")
               .title(S.L("Get an APK resource by its fully-qualified name"))
               .description(
                  S.L(
                     "Retrieve the contents of an APK structured resource file using its fully-qualified name, examples: 'values-v30/strings.xml', or 'layout/foo.txt'"
                  )
               )
               .inputSchema(this.pC((Type)GK.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            GK var3 = (GK)JsonUtil.mapToPojo(var2.arguments(), GK.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IApkUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IApkUnit)this.pC(var4, var3.pC, IApkUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The APK unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IApkUnit)var4.findUnit(Ws.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no APK unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The APK cannot be initialized, it cannot be used"));
               } else {
                  IUnit var6 = var5.getResources();
                  if (var6 == null) {
                     return this.kS(S.L("The APK does not contain resources"));
                  } else {
                     String var7 = var3.A;
                     byte var8 = 0;
                     String[] var9 = var7.split("/");
                     if (var9[0].equals("res")) {
                        var8 = 1;
                     }

                     if (var9.length - var8 != 2) {
                        return this.kS(S.L("Invalid resource path, the expected format is: 'resTypeFolder/resFileName'"));
                     } else {
                        IUnit var10 = UnitUtil.findChildByName(var6, var9[var8], 0);
                        if (var10 == null) {
                           return this.kS(S.L("Invalid resource path or resource not found"));
                        } else {
                           IUnit var11 = UnitUtil.findChildByName(var10, var9[var8 + 1], 0);
                           if (var11 == null) {
                              return this.kS(S.L("Invalid resource path or resource not found"));
                           } else {
                              String var12 = "No suitable document";
                              IUnitFormatter var13 = var11.getFormatter();

                              for (IUnitDocumentPresentation var15 : var13.getPresentations()) {
                                 IGenericDocument var16 = var15.getDocument();

                                 try {
                                    if (var16 instanceof ITextDocument var17) {
                                       var12 = var17.format();
                                       break;
                                    }
                                 } finally {
                                    var16.dispose();
                                 }
                              }

                              return this.A(var12);
                           }
                        }
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification ys() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("find_dynamic_library")
               .title(S.L("Find a dynamic library (located in an APK)"))
               .description(S.L("Find a dynamic library loaded by the dex bytecode of an APK, and provide the path to its associated code unit\n"))
               .inputSchema(this.pC((Type)oP.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            oP var3 = (oP)JsonUtil.mapToPojo(var2.arguments(), oP.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IApkUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IApkUnit)this.pC(var4, var3.pC, IApkUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The APK unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IApkUnit)var4.findUnit(Ws.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no APK unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The APK cannot be initialized, it cannot be used"));
               } else {
                  List var6 = null;
                  if (var5.getLibraries() != null) {
                     for (int var8 = 0; var8 < gp.length; var8++) {
                        AndroidPlatformABI var7 = gp[var8];
                        var6 = var5.getLibrariesForArch(var7);
                        if (var6 != null) {
                           break;
                        }
                     }
                  }

                  if (var6 != null && !var6.isEmpty()) {
                     String var11 = null;

                     for (IELFUnit var9 : var6) {
                        if (var9.getName().equals("lib" + var3.A + ".so")) {
                           IUnit var10 = var9.getImageUnit();
                           var11 = this.pC(var10);
                           break;
                        }
                     }

                     return var11 == null ? this.kS(S.L("The requested dynamic library was not found")) : this.A(var11);
                  } else {
                     return this.kS(S.L("No dynamic libraries found in the usual locations"));
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification ld() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_code_strings")
               .title(S.L("List the strings of a code unit"))
               .description(S.L("Retrieve the list of strings present in a code unit such as a dex, pe, elf, etc."))
               .inputSchema(this.pC((Type)io.class))
               .outputSchema(this.pC((Type)Sb.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            io var3 = (io)JsonUtil.mapToPojo(var2.arguments(), io.class);
            Sb var4 = new Sb();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  String var7 = var3.wS;
                  if (var7 == null || var7.isEmpty() || var7.equals("*")) {
                     var7 = null;
                  }

                  int var8 = Math.max(var3.kS, 100);
                  int var9 = 0;
                  int var10 = 0;

                  for (ICodeString var12 : var6.getStrings()) {
                     if (var9 >= var8) {
                        break;
                     }

                     if (!(var12 instanceof IDexString var13 && !Boolean.TRUE.equals(var13.getHintUsedAsImmediate()))) {
                        if (var7 == null) {
                           if (var10 >= var3.A) {
                              var4.kS.add(var12.getValue());
                              var9++;
                           }

                           var10++;
                        } else {
                           String var14 = var12.getValue();
                           if (Strings.starMatches(var14, var7)) {
                              if (var10 >= var3.A) {
                                 var4.kS.add(var12.getValue());
                                 var9++;
                              }

                              var10++;
                           }
                        }
                     }
                  }

                  return this.pC((KD)var4);
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification gp() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_internal_types")
               .title(S.L("List the internal types of a code unit"))
               .description(S.L("Retrieve the list of types (classes and interfaces) defined in a code unit, such as a Dex, PE, ELF, etc."))
               .inputSchema(this.pC((Type)eW.class))
               .outputSchema(this.pC((Type)jM.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            eW var3 = (eW)JsonUtil.mapToPojo(var2.arguments(), eW.class);
            jM var4 = new jM();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  CallToolResult var7 = this.pC(var6, var4);
                  if (var7 != null) {
                     return var7;
                  } else {
                     String var8 = var3.wS;
                     if (var8 == null || var8.isEmpty() || var8.equals("*")) {
                        var8 = null;
                     }

                     int var9 = Math.max(var3.kS, 100);
                     int var10 = 0;
                     int var11 = 0;

                     for (ICodeClass var13 : var6.getClasses()) {
                        if (var10 >= var9) {
                           break;
                        }

                        if (var13.isInternal()) {
                           if (var8 == null) {
                              if (var11 >= var3.A) {
                                 var4.kS.add(var13.getSignature());
                                 var10++;
                              }

                              var11++;
                           } else {
                              String var14 = var13.getSignature();
                              if (Strings.starMatches(var14, var8)) {
                                 if (var11 >= var3.A) {
                                    var4.kS.add(var14);
                                    var10++;
                                 }

                                 var11++;
                              }
                           }
                        }
                     }

                     return this.pC((KD)var4);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification oT() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("get_type_information")
               .title(S.L("Get type information"))
               .description(S.L("Retrieve information about a class or interface, such as its super types or implemented interfaces"))
               .inputSchema(this.pC((Type)Tb.class))
               .outputSchema(this.pC((Type)ma.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            Tb var3 = (Tb)JsonUtil.mapToPojo(var2.arguments(), Tb.class);
            ma var4 = new ma();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  CallToolResult var7 = this.pC(var6, var4);
                  if (var7 != null) {
                     return var7;
                  } else {
                     ICodeClass var8 = var6.getClass(var3.A);
                     if (var8 == null) {
                        return this.pC(var4, S.L("The type was not found"));
                     } else if (!var8.isInternal()) {
                        return this.pC(var4, S.L("The type is not an internal type, there is no available information about it"));
                     } else {
                        for (ICodeType var10 : var8.getSupertypes()) {
                           var4.kS.add(var10.getAddress());
                        }

                        for (ICodeType var12 : var8.getImplementedInterfaces()) {
                           var4.wS.add(var12.getAddress());
                        }

                        return this.pC((KD)var4);
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification fI() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_internal_methods")
               .title(S.L("List the internal methods"))
               .description(S.L("Retrieve the list of internal methods defined in a code unit or defined in a specified internal type\n"))
               .inputSchema(this.pC((Type)m.class))
               .outputSchema(this.pC((Type)jM.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            m var3 = (m)JsonUtil.mapToPojo(var2.arguments(), m.class);
            jM var4 = new jM();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  CallToolResult var7 = this.pC(var6, var4);
                  if (var7 != null) {
                     return var7;
                  } else {
                     String var8 = var3.wS;
                     if (var8 == null || var8.isEmpty() || var8.equals("*")) {
                        var8 = null;
                     }

                     int var9 = Math.max(var3.kS, 100);
                     int var10 = 0;
                     int var11 = 0;

                     for (ICodeMethod var13 : var6.getMethods()) {
                        if (var10 >= var9) {
                           break;
                        }

                        if (var13.isInternal()) {
                           if (var8 == null) {
                              if (var11 >= var3.A) {
                                 var4.kS.add(var13.getSignature());
                                 var10++;
                              }

                              var11++;
                           } else {
                              String var14 = var13.getSignature();
                              if (Strings.starMatches(var14, var8)) {
                                 if (var11 >= var3.A) {
                                    var4.kS.add(var14);
                                    var10++;
                                 }

                                 var11++;
                              }
                           }
                        }
                     }

                     return this.pC((KD)var4);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification WR() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_internal_fields")
               .title(S.L("List the internal fields"))
               .description(S.L("Retrieve the list of internal fields defined in a code unit or defined in a specified internal type\n"))
               .inputSchema(this.pC((Type)nA.class))
               .outputSchema(this.pC((Type)jM.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            nA var3 = (nA)JsonUtil.mapToPojo(var2.arguments(), nA.class);
            jM var4 = new jM();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  CallToolResult var7 = this.pC(var6, var4);
                  if (var7 != null) {
                     return var7;
                  } else {
                     String var8 = var3.wS;
                     if (var8 == null || var8.isEmpty() || var8.equals("*")) {
                        var8 = null;
                     }

                     int var9 = Math.max(var3.kS, 100);
                     int var10 = 0;
                     int var11 = 0;

                     for (ICodeField var13 : var6.getFields()) {
                        if (var10 >= var9) {
                           break;
                        }

                        if (var13.isInternal()) {
                           if (var8 == null) {
                              if (var11 >= var3.A) {
                                 var4.kS.add(var13.getSignature());
                                 var10++;
                              }

                              var11++;
                           } else {
                              String var14 = var13.getSignature();
                              if (Strings.starMatches(var14, var8)) {
                                 if (var11 >= var3.A) {
                                    var4.kS.add(var14);
                                    var10++;
                                 }

                                 var11++;
                              }
                           }
                        }
                     }

                     return this.pC((KD)var4);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification NS() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("decompile_dex_class")
               .title(S.L("Decompile a dex class"))
               .description(S.L("Decompile a dex class to Java pseudo code"))
               .inputSchema(this.pC((Type)HE.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            HE var3 = (HE)JsonUtil.mapToPojo(var2.arguments(), HE.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  IDexDecompilerUnit var6 = var5.getDecompiler();
                  if (var6 == null) {
                     return this.kS(S.L("There is no decompiler available"));
                  } else {
                     CallToolResult var7 = this.pC(var5, null);
                     if (var7 != null) {
                        return var7;
                     } else {
                        String var8 = this.pC(var3.A);
                        if (!var6.decompileClass(var8)) {
                           return this.kS(S.L("The decompilation failed"));
                        } else {
                           String var9 = var6.getDecompiledClassText(var3.A);
                           String var10 = Strings.ff("// Decompilation for type descriptor: %s\n%s", var8, var9);
                           return this.A(var10);
                        }
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification vP() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("decompile_dex_method")
               .title(S.L("Decompile a dex method"))
               .description(S.L("Decompile a dex method to Java pseudo code"))
               .inputSchema(this.pC((Type)qt.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            qt var3 = (qt)JsonUtil.mapToPojo(var2.arguments(), qt.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  IDexDecompilerUnit var6 = var5.getDecompiler();
                  if (var6 == null) {
                     return this.kS(S.L("There is no decompiler available"));
                  } else {
                     CallToolResult var7 = this.pC(var5, null);
                     if (var7 != null) {
                        return var7;
                     } else {
                        String var8 = var3.A + "->" + var3.kS;
                        if (!var6.decompileMethod(var8)) {
                           return this.kS(S.L("The decompilation failed"));
                        } else {
                           String var9 = var6.getDecompiledMethodText(var8);
                           String var10 = Strings.ff("// Decompilation for method: %s\n%s", var8, var9);
                           return this.A(var10);
                        }
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification xC() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("decompile_code_item")
               .title(S.L("Decompile a code item"))
               .description(S.L("Decompile a code item, such as a method or class, to pseudo code"))
               .inputSchema(this.pC((Type)sy.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            sy var3 = (sy)JsonUtil.mapToPojo(var2.arguments(), sy.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               ICodeUnit var5 = (ICodeUnit)this.pC(var4, var3.pC, ICodeUnit.class);
               if (var5 == null) {
                  return this.kS(S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var5)) {
                  return this.kS(S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  IDecompilerUnit var6 = DecompilerHelper.getDecompiler(var5);
                  if (var6 == null) {
                     return this.kS(S.L("There is no decompiler available"));
                  } else {
                     CallToolResult var7 = this.pC(var5, null);
                     if (var7 != null) {
                        return var7;
                     } else {
                        List var8 = List.of(var3.A);
                        if (!var6.decompile(var8, null)) {
                           return this.kS(S.L("The decompilation failed"));
                        } else {
                           String var9 = var6.getDecompiledText(var3.A);
                           String var10 = Strings.ff("// Decompilation of: %s\n%s", var3.A, var9);
                           return this.A(var10);
                        }
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification ED() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("bulk_rename_dex_items")
               .title(S.L("Bulk rename dex items"))
               .description(S.L("Rename dex items such as classes, methods and fields to names that are shorter and readable."))
               .inputSchema(this.pC((Type)yt.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            yt var3 = (yt)JsonUtil.mapToPojo(var2.arguments(), yt.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  DexBulkItemRenamer var6 = new DexBulkItemRenamer(var5, 7, 20, null);
                  if (!var6.process(false, true)) {
                     return this.kS(S.L("An error occurred during bulk renaming"));
                  } else {
                     String var7 = Strings.ff(S.L("%d items were renamed"), var6.getCountOfRenames());
                     return this.A(var7);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification Sc() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("rename_dex_items")
               .title(S.L("Rename dex items"))
               .description(
                  S.L("Rename one or more dex class, method or field to another name, which may be better or more descriptive than the current name.")
               )
               .inputSchema(this.pC((Type)Yd.class))
               .outputSchema(this.pC((Type)t.class))
               .build()
         )
         .callHandler(
            (var1, var2) -> {
               Yd var3 = (Yd)JsonUtil.mapToPojo(var2.arguments(), Yd.class);
               t var4 = new t();
               IRuntimeProject var5 = this.pC();
               if (var5 == null) {
                  return this.pC(var4, S.L("There is no opened project"));
               } else {
                  IDexUnit var6;
                  if (!Strings.isBlank(var3.pC)) {
                     var6 = (IDexUnit)this.pC(var5, var3.pC, IDexUnit.class);
                     if (var6 == null) {
                        return this.pC(var4, S.L("The dex unit with the provided path was not found in the project"));
                     }
                  } else {
                     var6 = (IDexUnit)var5.findUnit(IDexUnit.class);
                     if (var6 == null) {
                        return this.pC(var4, S.L("There is no dex unit in this project"));
                     }
                  }

                  if (!this.kS(var6)) {
                     return this.pC(var4, S.L("The dex unit cannot be initialized, it cannot be used"));
                  } else if (var3.A.isEmpty()) {
                     return this.pC(var4, S.L("You must specify at least one item to be renamed"));
                  } else {
                     for (Yd.Av var8 : var3.A) {
                        String var9 = var8.pC.trim();
                        Object var10 = null;
                        if (!var9.contains("->")) {
                           var10 = var6.getClass(var9);
                        } else {
                           var9 = var9.replace(":(", "(");
                           if (!var9.contains(":") && !var9.contains("(")) {
                              int var11 = var9.indexOf("->");
                              String var12 = var9.substring(0, var11);
                              String var13 = var9.substring(var11 + 2);
                              IDexClass var14 = var6.getClass(var12);
                              if (var14 != null) {
                                 IDexItem var15 = null;

                                 for (IDexItem var17 : var14.getFieldsAndMethods()) {
                                    if (var17.getName().equals(var13)) {
                                       if (var15 != null) {
                                          break;
                                       }

                                       var15 = var17;
                                    }
                                 }

                                 if (var15 != null) {
                                    var9 = var15.getSignature();
                                 }
                              }
                           }

                           if (!var9.contains(":")) {
                              if (var9.contains("(")) {
                                 var10 = var6.getMethod(var9);
                              }
                           } else {
                              var10 = var6.getField(var9);
                           }
                        }

                        if (var10 == null) {
                           var4.UT.add(new t.Av(var8.pC, S.L("The item was not found")));
                        } else if (!((IDexItem)var10).setName(var8.A)) {
                           var4.UT
                              .add(
                                 new t.Av(
                                    var8.pC, S.L("The item could not be renamed to the provided new name (it may be illegal or overlap with an existing name)")
                                 )
                              );
                        }
                     }

                     int var18 = var4.UT.size();
                     var4.wS = var18;
                     var4.kS = var3.A.size() - var18;
                     return this.pC((KD)var4);
                  }
               }
            }
         )
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification ah() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("rename_code_item")
               .title(S.L("Rename code item"))
               .description(S.L("Rename a class, method or field to another name, which may be better-suited or more descriptive than the original name."))
               .inputSchema(this.pC((Type)dE.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            dE var3 = (dE)JsonUtil.mapToPojo(var2.arguments(), dE.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               ICodeUnit var5 = (ICodeUnit)this.pC(var4, var3.pC, ICodeUnit.class);
               if (var5 == null) {
                  return this.kS(S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var5)) {
                  return this.kS(S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  boolean var6 = false;
                  if (var5 instanceof IDexUnit var7) {
                     if (!var3.A.contains("->")) {
                        IDexClass var9 = var7.getClass(var3.A);
                        if (var9 == null) {
                           return this.kS(S.L("The class cannot be found"));
                        }

                        var6 = var9.setName(var3.kS);
                     } else if (!var3.A.contains(":")) {
                        IDexMethod var12 = var7.getMethod(var3.A);
                        if (var12 == null) {
                           return this.kS(S.L("The method cannot be found"));
                        }

                        var6 = var12.setName(var3.kS);
                     } else {
                        IDexField var13 = var7.getField(var3.A);
                        if (var13 == null) {
                           return this.kS(S.L("The field cannot be found"));
                        }

                        var6 = var13.setName(var3.kS);
                     }
                  } else if (var5 instanceof INativeCodeUnit var8 && var8.getCodeItemByAddress(var3.A) instanceof INativeItem var10) {
                     var10.setName(var3.kS);
                     var6 = true;
                  }

                  if (!var6) {
                     return this.kS(S.L("Renaming failed"));
                  } else {
                     String var11 = S.L("The item was successfully renamed");
                     return this.A(var11);
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification eP() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("create_dex_package")
               .title(S.L("Create a dex package"))
               .description(S.L("Create a new dex code package"))
               .inputSchema(this.pC((Type)RC.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            RC var3 = (RC)JsonUtil.mapToPojo(var2.arguments(), RC.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  String var6 = var3.A;
                  IDexPackage var7 = var5.addPackage(var6);
                  return var7 == null ? this.kS(S.L("Package creation failed")) : this.A(S.L("The package was successfully created"));
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification UO() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("rename_dex_package")
               .title(S.L("Rename a dex package"))
               .description(S.L("Rename a dex code package to another name"))
               .inputSchema(this.pC((Type)BP.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            BP var3 = (BP)JsonUtil.mapToPojo(var2.arguments(), BP.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  String var6 = var3.A;
                  IDexPackage var7 = var5.getPackage(var6);
                  if (var7 == null) {
                     return this.kS(S.L("The package cannot be found"));
                  } else {
                     return !var7.setName(var3.kS) ? this.kS(S.L("The renaming operation failed")) : this.A(S.L("The package was successfully renamed"));
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification Ab() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("move_dex_class_to_package")
               .title(S.L("Move a dex class to a package"))
               .description(S.L("Move a dex class to another existing package"))
               .inputSchema(this.pC((Type)B.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            B var3 = (B)JsonUtil.mapToPojo(var2.arguments(), B.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  IDexClass var6 = var5.getClass(var3.A);
                  if (var6 == null) {
                     return this.kS(S.L("The class cannot be found"));
                  } else {
                     IDexPackage var7 = var5.getPackage(var3.kS);
                     if (var7 == null) {
                        return this.kS(S.L("The package cannot be found"));
                     } else {
                        return !var5.moveTo(var6, var7) ? this.kS(S.L("The moving operation failed")) : this.A(S.L("The class was successfully moved"));
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification rl() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("move_dex_package_to_package")
               .title(S.L("Move a dex package to another package"))
               .description(S.L("Move a source package inside a destination package"))
               .inputSchema(this.pC((Type)co.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            co var3 = (co)JsonUtil.mapToPojo(var2.arguments(), co.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               IDexUnit var5;
               if (!Strings.isBlank(var3.pC)) {
                  var5 = (IDexUnit)this.pC(var4, var3.pC, IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("The dex unit with the provided path was not found in the project"));
                  }
               } else {
                  var5 = (IDexUnit)var4.findUnit(IDexUnit.class);
                  if (var5 == null) {
                     return this.kS(S.L("There is no dex unit in this project"));
                  }
               }

               if (!this.kS(var5)) {
                  return this.kS(S.L("The dex unit cannot be initialized, it cannot be used"));
               } else {
                  IDexPackage var6 = var5.getPackage(var3.A);
                  if (var6 == null) {
                     return this.kS(S.L("The source package cannot be found"));
                  } else {
                     IDexPackage var7 = var5.getPackage(var3.kS);
                     if (var7 == null) {
                        return this.kS(S.L("The destination package cannot be found"));
                     } else {
                        return !var5.moveTo(var6, var7) ? this.kS(S.L("The moving operation failed")) : this.A(S.L("The package was successfully moved"));
                     }
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification z() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("rename_pseudo_code_variables")
               .title(S.L("Rename pseudo-code variables"))
               .description(
                  S.L(
                     "Rename one or more local variables or parameters defined in the decompiled pseudo-code of a method. The method must have been decompiled first."
                  )
               )
               .inputSchema(this.pC((Type)Pt.class))
               .outputSchema(this.pC((Type)lX.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            Pt var3 = (Pt)JsonUtil.mapToPojo(var2.arguments(), Pt.class);
            lX var4 = new lX();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else {
                  IDecompilerUnit var7 = DecompilerHelper.getDecompiler(var6);
                  if (var7 == null) {
                     return this.pC(var4, S.L("There is no decompiler available"));
                  } else if (var7 instanceof IDexDecompilerUnit var8) {
                     if (var8.getMethod(var3.A, false) == null) {
                        return this.kS(S.L("The method must be decompiled first, before attempting to rename some of its local variables or parameters"));
                     } else if (var3.kS.isEmpty()) {
                        return this.pC(var4, S.L("You must specify at least one item to be renamed"));
                     } else {
                        for (Pt.Av var10 : var3.kS) {
                           boolean var11 = var8.setIdentifierName(var3.A, var10.pC, var10.A);
                           if (!var11) {
                              var4.UT.add(new lX.Av(var10.pC, S.L("The local variable could not be renamed to the provided new name")));
                           }
                        }

                        int var12 = var4.UT.size();
                        var4.wS = var12;
                        var4.kS = var3.kS.size() - var12;
                        return this.pC((KD)var4);
                     }
                  } else {
                     return this.kS(S.L("Only local variables of dex decompilations can be renamed at the moment"));
                  }
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification Ek() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("get_disassembly_snippet")
               .title(S.L("Get a disassembly snippet"))
               .description(S.L("Retrieve a chunk of disassembly code around a provided address."))
               .inputSchema(this.pC((Type)Mh.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            Mh var3 = (Mh)JsonUtil.mapToPojo(var2.arguments(), Mh.class);
            IRuntimeProject var4 = this.pC();
            if (var4 == null) {
               return this.kS(S.L("There is no opened project"));
            } else {
               ICodeUnit var5 = (ICodeUnit)this.pC(var4, var3.pC, ICodeUnit.class);
               if (var5 == null) {
                  return this.kS(S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var5)) {
                  return this.kS(S.L("The code unit cannot be initialized, it cannot be used"));
               } else if (var3.A != null && var3.kS >= 0 && var3.wS >= 0) {
                  int var6 = Math.min(var3.kS, 30);
                  int var7 = Math.min(var3.wS, 20);
                  ITextDocument var9 = var5.getDisassemblyDocument();

                  CallToolResult var15;
                  try {
                     ICoordinates var10 = var9.addressToCoordinates(var3.A);
                     if (var10 == null) {
                        return this.kS(S.L("The address is invalid"));
                     }

                     long var11 = var10.getAnchorId();
                     int var13 = var10.getLineDelta();
                     ITextDocumentPart var14 = var9.getDocumentPart(var11, var13 + var6, var7 <= var13 ? 0 : var7 - var13);
                     if (var14 != null) {
                        String var8 = TextPartUtil.buildRawTextFromPart(var14);
                        return this.A(var8);
                     }

                     var15 = this.kS(S.L("The disassembly snippet cannot be retrieved"));
                  } finally {
                     var9.dispose();
                  }

                  return var15;
               } else {
                  return this.kS(S.L("Illegal arguments provided to the methods"));
               }
            }
         })
         .build();
   }

   @com.pnfsoftware.jeb.clienti.mcp.Av.Av
   SyncToolSpecification hK() {
      return SyncToolSpecification.builder()
         .tool(
            Tool.builder()
               .name("list_cross_references")
               .title(S.L("List cross-references"))
               .description(S.L("Retrieve cross-references to an address in a code unit, that is, the users or callers of the item at the provided address."))
               .inputSchema(this.pC((Type)gb.class))
               .outputSchema(this.pC((Type)Kr.class))
               .build()
         )
         .callHandler((var1, var2) -> {
            gb var3 = (gb)JsonUtil.mapToPojo(var2.arguments(), gb.class);
            Kr var4 = new Kr();
            IRuntimeProject var5 = this.pC();
            if (var5 == null) {
               return this.pC(var4, S.L("There is no opened project"));
            } else {
               ICodeUnit var6 = (ICodeUnit)this.pC(var5, var3.pC, ICodeUnit.class);
               if (var6 == null) {
                  return this.pC(var4, S.L("The code unit with the provided path was not found in the project"));
               } else if (!this.kS(var6)) {
                  return this.pC(var4, S.L("The code unit cannot be initialized, it cannot be used"));
               } else if (var3.A == null) {
                  return this.pC(var4, S.L("An address must be provided"));
               } else {
                  ActionXrefsData var7 = new ActionXrefsData();
                  if (!var6.prepareExecution(new ActionContext(var6, 4, 0L, var3.A), var7)) {
                     return this.pC(var4, S.L("The cross-references could not be retrieved"));
                  } else {
                     int var8 = Math.max(var3.wS, 100);
                     int var9 = 0;
                     int var10 = 0;

                     for (String var12 : var7.getAddresses()) {
                        if (var9 >= var8) {
                           break;
                        }

                        if (var10 >= var3.kS) {
                           var4.kS.add(var12);
                           var9++;
                        }

                        var10++;
                     }

                     return this.pC((KD)var4);
                  }
               }
            }
         })
         .build();
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.METHOD)
   public @interface Av {
      boolean pC() default false;
   }
}
