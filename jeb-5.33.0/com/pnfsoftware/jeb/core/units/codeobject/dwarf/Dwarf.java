package com.pnfsoftware.jeb.core.units.codeobject.dwarf;

public interface Dwarf {
   Dwarf.DwarfFormType address = Dwarf.DwarfFormType.address;
   Dwarf.DwarfFormType block = Dwarf.DwarfFormType.block;
   Dwarf.DwarfFormType constant = Dwarf.DwarfFormType.constant;
   Dwarf.DwarfFormType exprloc = Dwarf.DwarfFormType.exprloc;
   Dwarf.DwarfFormType flag = Dwarf.DwarfFormType.flag;
   Dwarf.DwarfFormType lineptr = Dwarf.DwarfFormType.lineptr;
   Dwarf.DwarfFormType loclist = Dwarf.DwarfFormType.loclist;
   Dwarf.DwarfFormType loclistptr = Dwarf.DwarfFormType.loclistptr;
   Dwarf.DwarfFormType macptr = Dwarf.DwarfFormType.macptr;
   Dwarf.DwarfFormType rangelistptr = Dwarf.DwarfFormType.rangelistptr;
   Dwarf.DwarfFormType reference = Dwarf.DwarfFormType.reference;
   Dwarf.DwarfFormType rnglist = Dwarf.DwarfFormType.rnglist;
   Dwarf.DwarfFormType string = Dwarf.DwarfFormType.string;

   public static enum DwarfAttribute {
      DW_AT_sibling(1, Dwarf.reference),
      DW_AT_location(2, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_name(3, Dwarf.string),
      DW_AT_ordering(9, Dwarf.constant),
      DW_AT_byte_size(11, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_bit_size(13, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_stmt_list(16, Dwarf.lineptr),
      DW_AT_low_pc(17, Dwarf.address),
      DW_AT_high_pc(18, Dwarf.address, Dwarf.constant),
      DW_AT_language(19, Dwarf.constant),
      DW_AT_discr(21, Dwarf.reference),
      DW_AT_discr_value(22, Dwarf.constant),
      DW_AT_visibility(23, Dwarf.constant),
      DW_AT_import(24, Dwarf.reference),
      DW_AT_string_length(25, Dwarf.exprloc, Dwarf.loclist, Dwarf.reference),
      DW_AT_common_reference(26, Dwarf.reference),
      DW_AT_comp_dir(27, Dwarf.string),
      DW_AT_const_value(28, Dwarf.block, Dwarf.constant, Dwarf.string),
      DW_AT_containing_type(29, Dwarf.reference),
      DW_AT_default_value(30, Dwarf.constant, Dwarf.reference, Dwarf.flag),
      DW_AT_inline(32, Dwarf.constant),
      DW_AT_is_optional(33, Dwarf.flag),
      DW_AT_lower_bound(34, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_producer(37, Dwarf.string),
      DW_AT_prototyped(39, Dwarf.flag),
      DW_AT_return_addr(42, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_start_scope(44, Dwarf.constant, Dwarf.rnglist),
      DW_AT_bit_stride(46, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_upper_bound(47, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_abstract_origin(49, Dwarf.reference),
      DW_AT_accessibility(50, Dwarf.constant),
      DW_AT_address_class(51, Dwarf.constant),
      DW_AT_artificial(52, Dwarf.flag),
      DW_AT_base_types(53, Dwarf.reference),
      DW_AT_calling_convention(54, Dwarf.constant),
      DW_AT_count(55, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_data_member_location(56, Dwarf.constant, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_decl_column(57, Dwarf.constant),
      DW_AT_decl_file(58, Dwarf.constant),
      DW_AT_decl_line(59, Dwarf.constant),
      DW_AT_declaration(60, Dwarf.flag),
      DW_AT_discr_list(61, Dwarf.block),
      DW_AT_encoding(62, Dwarf.constant),
      DW_AT_external(63, Dwarf.flag),
      DW_AT_frame_base(64, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_friend(65, Dwarf.reference),
      DW_AT_identifier_case(66, Dwarf.constant),
      DW_AT_namelist_item(68, Dwarf.reference),
      DW_AT_priority(69, Dwarf.reference),
      DW_AT_segment(70, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_specification(71, Dwarf.reference),
      DW_AT_static_link(72, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_type(73, Dwarf.reference),
      DW_AT_use_location(74, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_variable_parameter(75, Dwarf.flag),
      DW_AT_virtuality(76, Dwarf.constant),
      DW_AT_vtable_elem_location(77, Dwarf.exprloc, Dwarf.loclist),
      DW_AT_allocated(78, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_associated(79, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_data_location(80, Dwarf.exprloc),
      DW_AT_byte_stride(81, Dwarf.constant, Dwarf.exprloc, Dwarf.reference),
      DW_AT_entry_pc(82, Dwarf.address, Dwarf.constant),
      DW_AT_use_UTF8(83, Dwarf.flag),
      DW_AT_extension(84, Dwarf.reference),
      DW_AT_ranges(85, Dwarf.rnglist),
      DW_AT_trampoline(86, Dwarf.address, Dwarf.flag, Dwarf.reference, Dwarf.string),
      DW_AT_call_column(87, Dwarf.constant),
      DW_AT_call_file(88, Dwarf.constant),
      DW_AT_call_line(89, Dwarf.constant),
      DW_AT_description(90, Dwarf.string),
      DW_AT_binary_scale(91, Dwarf.constant),
      DW_AT_decimal_scale(92, Dwarf.constant),
      DW_AT_small(93, Dwarf.reference),
      DW_AT_decimal_sign(94, Dwarf.constant),
      DW_AT_digit_count(95, Dwarf.constant),
      DW_AT_picture_string(96, Dwarf.string),
      DW_AT_mutable(97, Dwarf.flag),
      DW_AT_threads_scaled(98, Dwarf.flag),
      DW_AT_explicit(99, Dwarf.flag),
      DW_AT_object_pointer(100, Dwarf.reference),
      DW_AT_endianity(101, Dwarf.constant),
      DW_AT_elemental(102, Dwarf.flag),
      DW_AT_pure(103, Dwarf.flag),
      DW_AT_recursive(104, Dwarf.flag),
      DW_AT_signature(105, Dwarf.reference),
      DW_AT_main_subprogram(106, Dwarf.flag),
      DW_AT_data_bit_offset(107, Dwarf.constant),
      DW_AT_const_expr(108, Dwarf.flag),
      DW_AT_enum_class(109, Dwarf.flag),
      DW_AT_linkage_name(110, Dwarf.string),
      DW_AT_string_length_bit_size(111),
      DW_AT_string_length_byte_size(112),
      DW_AT_rank(113),
      DW_AT_str_offsets_base(114),
      DW_AT_addr_base(115),
      DW_AT_rnglists_base(116),
      DW_AT_dwo_name(118),
      DW_AT_reference(119),
      DW_AT_rvalue_reference(120),
      DW_AT_macros(121),
      DW_AT_call_all_calls(122),
      DW_AT_call_all_source_calls(123),
      DW_AT_call_all_tail_calls(124),
      DW_AT_call_return_pc(125),
      DW_AT_call_value(126),
      DW_AT_call_origin(127),
      DW_AT_call_parameter(128),
      DW_AT_call_pc(129),
      DW_AT_call_tail_call(130),
      DW_AT_call_target(131),
      DW_AT_call_target_clobbered(132),
      DW_AT_call_data_location(133),
      DW_AT_call_data_value(134),
      DW_AT_noreturn(135),
      DW_AT_alignment(136),
      DW_AT_export_symbols(137),
      DW_AT_deleted(138),
      DW_AT_defaulted(139),
      DW_AT_loclists_base(140),
      DW_AT_MIPS_fde(8193),
      DW_AT_MIPS_loop_begin(8194),
      DW_AT_MIPS_tail_loop_begin(8195),
      DW_AT_MIPS_epilog_begin(8196),
      DW_AT_MIPS_loop_unroll_factor(8197),
      DW_AT_MIPS_software_pipeline_depth(8198),
      DW_AT_MIPS_linkage_name(8199),
      DW_AT_MIPS_stride(8200),
      DW_AT_MIPS_abstract_name(8201),
      DW_AT_MIPS_clone_origin(8202),
      DW_AT_MIPS_has_inlines(8203),
      DW_AT_HP_block_index(8192),
      DW_AT_HP_actuals_stmt_list(8208),
      DW_AT_HP_proc_per_section(8209),
      DW_AT_HP_raw_data_ptr(8210),
      DW_AT_HP_pass_by_reference(8211),
      DW_AT_HP_opt_level(8212),
      DW_AT_HP_prof_version_id(8213),
      DW_AT_HP_opt_flags(8214),
      DW_AT_HP_cold_region_low_pc(8215),
      DW_AT_HP_cold_region_high_pc(8216),
      DW_AT_HP_all_variables_modifiable(8217),
      DW_AT_HP_linkage_name(8218),
      DW_AT_HP_prof_flags(8219),
      DW_AT_HP_unit_name(8223),
      DW_AT_HP_unit_size(8224),
      DW_AT_HP_widened_byte_size(8225),
      DW_AT_HP_definition_points(8226),
      DW_AT_HP_default_location(8227),
      DW_AT_HP_is_result_param(8233),
      DW_AT_sf_names(8449),
      DW_AT_src_info(8450),
      DW_AT_mac_info(8451),
      DW_AT_src_coords(8452),
      DW_AT_body_begin(8453),
      DW_AT_body_end(8454),
      DW_AT_GNU_vector(8455),
      DW_AT_GNU_guarded_by(8456),
      DW_AT_GNU_pt_guarded_by(8457),
      DW_AT_GNU_guarded(8458),
      DW_AT_GNU_pt_guarded(8459),
      DW_AT_GNU_locks_excluded(8460),
      DW_AT_GNU_exclusive_locks_required(8461),
      DW_AT_GNU_shared_locks_required(8462),
      DW_AT_GNU_odr_signature(8463),
      DW_AT_GNU_template_name(8464),
      DW_AT_GNU_call_site_value(8465),
      DW_AT_GNU_call_site_data_value(8466),
      DW_AT_GNU_call_site_target(8467),
      DW_AT_GNU_call_site_target_clobbered(8468),
      DW_AT_GNU_tail_call(8469),
      DW_AT_GNU_all_tail_call_sites(8470),
      DW_AT_GNU_all_call_sites(8471),
      DW_AT_GNU_all_source_call_sites(8472),
      DW_AT_GNU_macros(8473),
      DW_AT_GNU_deleted(8474),
      DW_AT_GNU_dwo_name(8496),
      DW_AT_GNU_dwo_id(8497),
      DW_AT_GNU_ranges_base(8498),
      DW_AT_GNU_addr_base(8499),
      DW_AT_GNU_pubnames(8500),
      DW_AT_GNU_pubtypes(8501),
      DW_AT_GNU_discriminator(8502),
      DW_AT_GNU_locviews(8503),
      DW_AT_GNU_entry_view(8504),
      DW_AT_VMS_rtnbeg_pd_address(8705),
      DW_AT_use_GNAT_descriptive_type(8961),
      DW_AT_GNAT_descriptive_type(8962),
      DW_AT_GNU_numerator(8963),
      DW_AT_GNU_denominator(8964),
      DW_AT_GNU_bias(8965),
      DW_AT_upc_threads_scaled(12816),
      DW_AT_PGI_lbase(14848),
      DW_AT_PGI_soffset(14849),
      DW_AT_PGI_lstride(14850),
      DW_AT_APPLE_optimized(16353),
      DW_AT_APPLE_flags(16354),
      DW_AT_APPLE_isa(16355),
      DW_AT_APPLE_block(16356),
      DW_AT_APPLE_major_runtime_vers(16357),
      DW_AT_APPLE_runtime_class(16358),
      DW_AT_APPLE_omit_frame_ptr(16359),
      DW_AT_APPLE_property_name(16360),
      DW_AT_APPLE_property_getter(16361),
      DW_AT_APPLE_property_setter(16362),
      DW_AT_APPLE_property_attribute(16363),
      DW_AT_APPLE_objc_complete_type(16364),
      DW_AT_APPLE_property(16365),
      DW_AT_lo_user(8192),
      DW_AT_hi_user(16383);

      private final int value;

      private DwarfAttribute(int var3, Dwarf.DwarfFormType... var4) {
         this.value = var3;
      }

      public static Dwarf.DwarfAttribute getByValue(int var0) {
         for (Dwarf.DwarfAttribute var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         return null;
      }
   }

   public static enum DwarfBaseTypeAttributeEncoding {
      DW_ATE_address(1),
      DW_ATE_boolean(2),
      DW_ATE_complex_float(3),
      DW_ATE_float(4),
      DW_ATE_signed(5),
      DW_ATE_signed_char(6),
      DW_ATE_unsigned(7),
      DW_ATE_unsigned_char(8),
      DW_ATE_imaginary_float(9),
      DW_ATE_packed_decimal(10),
      DW_ATE_numeric_string(11),
      DW_ATE_edited(12),
      DW_ATE_signed_fixed(13),
      DW_ATE_unsigned_fixed(14),
      DW_ATE_decimal_float(15),
      DW_ATE_UTF(16),
      DW_ATE_UCS(17),
      DW_ATE_ASCII(18),
      DW_ATE_lo_user(128),
      DW_ATE_hi_user(255);

      private final int value;

      private DwarfBaseTypeAttributeEncoding(int var3) {
         this.value = var3;
      }

      public static Dwarf.DwarfBaseTypeAttributeEncoding getByValue(int var0) {
         for (Dwarf.DwarfBaseTypeAttributeEncoding var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         return null;
      }

      public int getValue() {
         return this.value;
      }
   }

   public static enum DwarfForm {
      DW_FORM_addr(1, Dwarf.address),
      DW_FORM_block2(3, Dwarf.block),
      DW_FORM_block4(4, Dwarf.block),
      DW_FORM_data2(5, Dwarf.constant),
      DW_FORM_data4(6, Dwarf.constant),
      DW_FORM_data8(7, Dwarf.constant),
      DW_FORM_string(8, Dwarf.string),
      DW_FORM_block(9, Dwarf.block),
      DW_FORM_block1(10, Dwarf.block),
      DW_FORM_data1(11, Dwarf.constant),
      DW_FORM_flag(12, Dwarf.flag),
      DW_FORM_sdata(13, Dwarf.constant),
      DW_FORM_strp(14, Dwarf.string),
      DW_FORM_udata(15, Dwarf.constant),
      DW_FORM_ref_addr(16, Dwarf.reference),
      DW_FORM_ref1(17, Dwarf.reference),
      DW_FORM_ref2(18, Dwarf.reference),
      DW_FORM_ref4(19, Dwarf.reference),
      DW_FORM_ref8(20, Dwarf.reference),
      DW_FORM_ref_udata(21, Dwarf.reference),
      DW_FORM_indirect(22, null),
      DW_FORM_sec_offset(23, Dwarf.lineptr),
      DW_FORM_exprloc(24, Dwarf.exprloc),
      DW_FORM_flag_present(25, Dwarf.flag),
      DW_FORM_strx(26, Dwarf.string),
      DW_FORM_addrx(27, Dwarf.address),
      DW_FORM_ref_sup4(28, Dwarf.reference),
      DW_FORM_strp_sup(29, Dwarf.string),
      DW_FORM_data16(30, Dwarf.constant),
      DW_FORM_line_strp(31, Dwarf.string),
      DW_FORM_implicit_const(33, Dwarf.constant),
      DW_FORM_loclistx(34, Dwarf.loclist),
      DW_FORM_rnglistx(35, Dwarf.rnglist),
      DW_FORM_ref_sup8(36, Dwarf.reference),
      DW_FORM_strx1(37, Dwarf.string),
      DW_FORM_strx2(38, Dwarf.string),
      DW_FORM_strx3(39, Dwarf.string),
      DW_FORM_strx4(40, Dwarf.string),
      DW_FORM_addrx1(41, Dwarf.address),
      DW_FORM_addrx2(42, Dwarf.address),
      DW_FORM_addrx3(43, Dwarf.address),
      DW_FORM_addrx4(44, Dwarf.address),
      DW_FORM_ref_sig8(32, Dwarf.reference),
      DW_FORM_GNU_addr_index(7937, Dwarf.address),
      DW_FORM_GNU_str_index(7938, Dwarf.string),
      DW_FORM_GNU_ref_alt(7968, Dwarf.reference),
      DW_FORM_GNU_strp_alt(7969, Dwarf.string);

      private final int value;
      private final Dwarf.DwarfFormType class_;

      private DwarfForm(int var3, Dwarf.DwarfFormType var4) {
         this.value = var3;
         this.class_ = var4;
      }

      public int getValue() {
         return this.value;
      }

      public Dwarf.DwarfFormType getType() {
         return this.class_;
      }

      public static Dwarf.DwarfForm getByValue(int var0) {
         for (Dwarf.DwarfForm var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         return null;
      }
   }

   public static enum DwarfFormType {
      address,
      block,
      constant,
      exprloc,
      flag,
      lineptr,
      loclist,
      loclistptr,
      macptr,
      rangelistptr,
      reference,
      rnglist,
      string;
   }

   public static enum DwarfTag {
      DW_TAG_array_type(1),
      DW_TAG_class_type(2),
      DW_TAG_entry_point(3),
      DW_TAG_enumeration_type(4),
      DW_TAG_formal_parameter(5),
      DW_TAG_imported_declaration(8),
      DW_TAG_label(10),
      DW_TAG_lexical_block(11),
      DW_TAG_member(13),
      DW_TAG_pointer_type(15),
      DW_TAG_reference_type(16),
      DW_TAG_compile_unit(17),
      DW_TAG_string_type(18),
      DW_TAG_structure_type(19),
      DW_TAG_subroutine_type(21),
      DW_TAG_typedef(22),
      DW_TAG_union_type(23),
      DW_TAG_unspecified_parameters(24),
      DW_TAG_variant(25),
      DW_TAG_common_block(26),
      DW_TAG_common_inclusion(27),
      DW_TAG_inheritance(28),
      DW_TAG_inlined_subroutine(29),
      DW_TAG_module(30),
      DW_TAG_ptr_to_member_type(31),
      DW_TAG_set_type(32),
      DW_TAG_subrange_type(33),
      DW_TAG_with_stmt(34),
      DW_TAG_access_declaration(35),
      DW_TAG_base_type(36),
      DW_TAG_catch_block(37),
      DW_TAG_const_type(38),
      DW_TAG_constant(39),
      DW_TAG_enumerator(40),
      DW_TAG_file_type(41),
      DW_TAG_friend(42),
      DW_TAG_namelist(43),
      DW_TAG_namelist_item(44),
      DW_TAG_packed_type(45),
      DW_TAG_subprogram(46),
      DW_TAG_template_type_parameter(47),
      DW_TAG_template_value_parameter(48),
      DW_TAG_thrown_type(49),
      DW_TAG_try_block(50),
      DW_TAG_variant_part(51),
      DW_TAG_variable(52),
      DW_TAG_volatile_type(53),
      DW_TAG_dwarf_procedure(54),
      DW_TAG_restrict_type(55),
      DW_TAG_interface_type(56),
      DW_TAG_namespace(57),
      DW_TAG_imported_module(58),
      DW_TAG_unspecified_type(59),
      DW_TAG_partial_unit(60),
      DW_TAG_imported_unit(61),
      DW_TAG_shared_type(64),
      DW_TAG_type_unit(65),
      DW_TAG_rvalue_reference_type(66),
      DW_TAG_template_alias(67),
      DW_TAG_coarray_type(68),
      DW_TAG_generic_subrange(69),
      DW_TAG_dynamic_type(70),
      DW_TAG_atomic_type(71),
      DW_TAG_call_site(72),
      DW_TAG_call_site_parameter(73),
      DW_TAG_skeleton_unit(74),
      DW_TAG_immutable_type(75),
      DW_TAG_lo_user(16512),
      DW_TAG_hi_user(65535),
      DW_TAG_MIPS_loop(16513),
      DW_TAG_HP_array_descriptor(16528),
      DW_TAG_HP_Bliss_field(16529),
      DW_TAG_HP_Bliss_field_set(16530),
      DW_TAG_format_label(16641),
      DW_TAG_function_template(16642),
      DW_TAG_class_template(16643),
      DW_TAG_GNU_BINCL(16644),
      DW_TAG_GNU_EINCL(16645),
      DW_TAG_GNU_template_template_param(16646),
      DW_TAG_GNU_template_parameter_pack(16647),
      DW_TAG_GNU_formal_parameter_pack(16648),
      DW_TAG_GNU_call_site(16649),
      DW_TAG_GNU_call_site_parameter(16650),
      DW_TAG_upc_shared_type(34661),
      DW_TAG_upc_strict_type(34662),
      DW_TAG_upc_relaxed_type(34663),
      DW_TAG_PGI_kanji_type(40960),
      DW_TAG_PGI_interface_block(40992);

      private final int value;

      private DwarfTag(int var3) {
         this.value = var3;
      }

      public static Dwarf.DwarfTag getByValue(int var0) {
         for (Dwarf.DwarfTag var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         return null;
      }

      public int getValue() {
         return this.value;
      }
   }
}
