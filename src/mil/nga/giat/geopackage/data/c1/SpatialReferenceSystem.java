package mil.nga.giat.geopackage.data.c1;

import mil.nga.giat.geopackage.data.c2.Contents;
import mil.nga.giat.geopackage.data.c3.GeometryColumns;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Spatial Reference System object. The coordinate reference system definitions
 * it contains are referenced by the GeoPackage {@link Contents} and
 * {@link GeometryColumns} objects to relate the vector and tile data in user
 * tables to locations on the earth.
 * 
 * @author osbornb
 */
@DatabaseTable(tableName = "gpkg_spatial_ref_sys", daoClass = SpatialReferenceSystemDao.class)
public class SpatialReferenceSystem {

	/**
	 * Table name
	 */
	public static final String TABLE_NAME = "gpkg_spatial_ref_sys";

	/**
	 * srsName field name
	 */
	public static final String COLUMN_SRS_NAME = "srs_name";

	/**
	 * srsId field name
	 */
	public static final String COLUMN_SRS_ID = "srs_id";

	/**
	 * id field name, srsId
	 */
	public static final String COLUMN_ID = COLUMN_SRS_ID;

	/**
	 * organization field name
	 */
	public static final String COLUMN_ORGANIZATION = "organization";

	/**
	 * organizationCoordsysId field name
	 */
	public static final String COLUMN_ORGANIZATION_COORDSYS_ID = "organization_coordsys_id";

	/**
	 * definition field name
	 */
	public static final String COLUMN_DEFINITION = "definition";

	/**
	 * description field name
	 */
	public static final String COLUMN_DESCRIPTION = "description";

	/**
	 * Human readable name of this SRS
	 */
	@DatabaseField(columnName = COLUMN_SRS_NAME, canBeNull = false)
	private String srsName;

	/**
	 * Unique identifier for each Spatial Reference System within a GeoPackage
	 */
	@DatabaseField(columnName = COLUMN_SRS_ID, id = true, canBeNull = false)
	private int srsId;

	/**
	 * Case-insensitive name of the defining organization e.g. EPSG or epsg
	 */
	@DatabaseField(columnName = COLUMN_ORGANIZATION, canBeNull = false)
	private String organization;

	/**
	 * Numeric ID of the Spatial Reference System assigned by the organization
	 */
	@DatabaseField(columnName = COLUMN_ORGANIZATION_COORDSYS_ID, canBeNull = false)
	private int organizationCoordsysId;

	/**
	 * Well-known Text [32] Representation of the Spatial Reference System
	 */
	@DatabaseField(columnName = COLUMN_DEFINITION, canBeNull = false)
	private String definition;

	/**
	 * Human readable description of this SRS
	 */
	@DatabaseField(columnName = COLUMN_DESCRIPTION)
	private String description;

	/**
	 * Contents
	 */
	@ForeignCollectionField(eager = false)
	private ForeignCollection<Contents> contents;

	/**
	 * Geometry Columns
	 */
	@ForeignCollectionField(eager = false)
	private ForeignCollection<GeometryColumns> geometryColumns;

	/**
	 * Default Constructor
	 */
	public SpatialReferenceSystem() {

	}

	public int getId() {
		return srsId;
	}

	public void setId(int id) {
		this.srsId = id;
	}

	public String getSrsName() {
		return srsName;
	}

	public void setSrsName(String srsName) {
		this.srsName = srsName;
	}

	public int getSrsId() {
		return srsId;
	}

	public void setSrsId(int srsId) {
		this.srsId = srsId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getOrganizationCoordsysId() {
		return organizationCoordsysId;
	}

	public void setOrganizationCoordsysId(int organizationCoordsysId) {
		this.organizationCoordsysId = organizationCoordsysId;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ForeignCollection<Contents> getContents() {
		return contents;
	}

	public ForeignCollection<GeometryColumns> getGeometryColumns() {
		return geometryColumns;
	}

}
