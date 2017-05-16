package tyrant.common.config;

import org.apache.commons.lang.StringUtils;
import org.hibernate.boot.model.naming.*;
import org.hibernate.internal.util.StringHelper;

/**
 * Created by zhangli on 9/5/2017.
 */
public class MySQLUpperCaseStrategyT extends ImplicitNamingStrategyJpaCompliantImpl {
    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        return super.determinePrimaryTableName(source);
    }

    @Override
    protected String transformEntityName(EntityNaming entityNaming) {
        return super.transformEntityName(entityNaming);
    }

    @Override
    public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
        return super.determineJoinTableName(source);
    }

    @Override
    public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source) {
        return super.determineCollectionTableName(source);
    }

    private Identifier convert(Identifier identifier) {
        if (identifier == null || StringUtils.isBlank(identifier.getText())) {
            return identifier;
        }

        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        String newName = identifier.getText().replaceAll(regex, replacement).toUpperCase();
        return Identifier.toIdentifier(newName);
    }
}
