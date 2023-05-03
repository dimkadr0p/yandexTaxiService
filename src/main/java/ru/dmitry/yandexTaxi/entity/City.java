package ru.dmitry.yandexTaxi.entity;

import lombok.*;

import javax.persistence.*;


//{"response":{"GeoObjectCollection":{"metaDataProperty":{"GeocoderResponseMetaData":{"request":"Людиново","results":"10","found":"6"}},"featureMember":[{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"other","text":"Россия, Калужская область, Людиново","kind":"locality","Address":{"country_code":"RU","formatted":"Россия, Калужская область, Людиново","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"province","name":"Калужская область"},{"kind":"area","name":"Людиновский район"},{"kind":"area","name":"городское поселение Людиново"},{"kind":"locality","name":"Людиново"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Калужская область, Людиново","CountryNameCode":"RU","CountryName":"Россия","AdministrativeArea":{"AdministrativeAreaName":"Калужская область","SubAdministrativeArea":{"SubAdministrativeAreaName":"Людиновский район","Locality":{"LocalityName":"Людиново"}}}}}}},"name":"Людиново","description":"Калужская область, Россия","boundedBy":{"Envelope":{"lowerCorner":"34.380287 53.800017","upperCorner":"34.503266 53.891147"}},"Point":{"pos":"34.447732 53.864533"}}},{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"other","text":"Россия, Московская железная дорога, станция Людиново-2","kind":"railway_station","Address":{"country_code":"RU","formatted":"Россия, Московская железная дорога, станция Людиново-2","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"route","name":"Московская железная дорога"},{"kind":"railway_station","name":"станция Людиново-2"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Московская железная дорога, станция Людиново-2","CountryNameCode":"RU","CountryName":"Россия","Country":{"Locality":{}}}}}},"name":"станция Людиново-2","description":"Московская железная дорога, Россия","boundedBy":{"Envelope":{"lowerCorner":"34.418294 53.883129","upperCorner":"34.434751 53.892851"}},"Point":{"pos":"34.426523 53.88799"}}},{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"other","text":"Россия, Московская железная дорога, станция Людиново-1","kind":"railway_station","Address":{"country_code":"RU","formatted":"Россия, Московская железная дорога, станция Людиново-1","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"route","name":"Московская железная дорога"},{"kind":"railway_station","name":"станция Людиново-1"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Московская железная дорога, станция Людиново-1","CountryNameCode":"RU","CountryName":"Россия","Country":{"Locality":{}}}}}},"name":"станция Людиново-1","description":"Московская железная дорога, Россия","boundedBy":{"Envelope":{"lowerCorner":"34.423028 53.826131","upperCorner":"34.439486 53.835866"}},"Point":{"pos":"34.431257 53.830999"}}},{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"street","text":"Россия, Калужская область, 29К-004","kind":"street","Address":{"country_code":"RU","formatted":"Россия, Калужская область, 29К-004","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"province","name":"Калужская область"},{"kind":"street","name":"29К-004"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Калужская область, 29К-004","CountryNameCode":"RU","CountryName":"Россия","AdministrativeArea":{"AdministrativeAreaName":"Калужская область","Locality":{"Thoroughfare":{"ThoroughfareName":"29К-004"}}}}}}},"name":"29К-004","description":"Калужская область, Россия","boundedBy":{"Envelope":{"lowerCorner":"34.294839 53.780442","upperCorner":"34.421232 54.443434"}},"Point":{"pos":"34.341246 54.119983"}}},{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"street","text":"Россия, Калужская область, 29К-011","kind":"street","Address":{"country_code":"RU","formatted":"Россия, Калужская область, 29К-011","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"province","name":"Калужская область"},{"kind":"street","name":"29К-011"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Калужская область, 29К-011","CountryNameCode":"RU","CountryName":"Россия","AdministrativeArea":{"AdministrativeAreaName":"Калужская область","Locality":{"Thoroughfare":{"ThoroughfareName":"29К-011"}}}}}}},"name":"29К-011","description":"Калужская область, Россия","boundedBy":{"Envelope":{"lowerCorner":"34.416156 53.751468","upperCorner":"34.806888 53.867357"}},"Point":{"pos":"34.608513 53.802846"}}},{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"precision":"street","text":"Россия, Калужская область, 29Н-264","kind":"street","Address":{"country_code":"RU","formatted":"Россия, Калужская область, 29Н-264","Components":[{"kind":"country","name":"Россия"},{"kind":"province","name":"Центральный федеральный округ"},{"kind":"province","name":"Калужская область"},{"kind":"street","name":"29Н-264"}]},"AddressDetails":{"Country":{"AddressLine":"Россия, Калужская область, 29Н-264","CountryNameCode":"RU","CountryName":"Россия","AdministrativeArea":{"AdministrativeAreaName":"Калужская область","Locality":{"Thoroughfare":{"ThoroughfareName":"29Н-264"}}}}}}},"name":"29Н-264","description":"Калужская область, Россия","boundedBy":{"Envelope":{"lowerCorner":"34.416156 53.862595","upperCorner":"34.430449 53.867357"}},"Point":{"pos":"34.423253 53.864841"}}}]}}}

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable=false)
    private String name;
    @Column(unique = true, nullable=false)
    private String coordinates;
}