package beans.dreamteam.musicbeans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import beans.dreamteam.musicbeans.utils.Singleton;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private FavoritesFragment favoritesFragment;
    private NewsFragment newsFragment;
    private SearchFragment searchFragment;
    private static final String TAG_FRAGMENT_FAVORITES = "tag_frag_favorites";
    private static final String TAG_FRAGMENT_HOME = "tag_frag_home";
    private static final String TAG_FRAGMENT_SEARCH = "tag_frag_search";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar); // get the reference of custom Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // drawer menu initialization

        drawerLayout = findViewById(R.id.drawer_activity);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        NavigationView navigationView =  findViewById(R.id.navigation_view);
        View hView =  navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView menuUsermail = hView.findViewById(R.id.menu_user_email);
        TextView menuUserName = hView.findViewById(R.id.menu_user_name);
        menuUsermail.setText(Singleton.getInstance().usuarioLogeado.getCorreo());
        menuUserName.setText(Singleton.getInstance().usuarioLogeado.getNombre());

            bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.bottombaritem_home:
                                    switchFragment(0, TAG_FRAGMENT_HOME);
                                    return true;
                                case R.id.bottombaritem_search:
                                    switchFragment(1, TAG_FRAGMENT_SEARCH);
                                    return true;
                                case R.id.bottombaritem_favorites:
                                    switchFragment(2, TAG_FRAGMENT_FAVORITES);
                                    return true;
                            }
                            return false;
                        }
                    });
        if(!Singleton.getInstance().usuarioLogeado.getTipo().toLowerCase().equals("cliente")) {
            bottomNavigationView.setVisibility(View.INVISIBLE);
        }
            buildFragmentsList();
            switchFragment(0, TAG_FRAGMENT_FAVORITES);

    }
    private FavoritesFragment buildFavoriteFragment() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FavoritesFragment.ARG_TITLE, "Favoritos");
        fragment.setArguments(bundle);
        return fragment;
    }
    private void buildFragmentsList() {
        favoritesFragment = buildFavoriteFragment();
        newsFragment = buildNewsFragment();
        searchFragment = buildSearchFragment();
    }

    private NewsFragment buildNewsFragment() {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FavoritesFragment.ARG_TITLE, "Noticias");
        fragment.setArguments(bundle);
        return fragment;
    }
    private SearchFragment buildSearchFragment() {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FavoritesFragment.ARG_TITLE, "Busqueda");
        fragment.setArguments(bundle);
        return fragment;
    }


    private void switchFragment(int pos, String tag) {
        switch(pos) {
            case 0:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_fragmentholder, newsFragment, tag)
                        .commit();
                break;
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_fragmentholder, searchFragment, tag)
                        .commit();
                break;
            case 2:
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_fragmentholder, favoritesFragment, tag)
                    .commit();
            break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.menu_drawer_logout: {
                Toast.makeText(this, R.string.logut_success, Toast.LENGTH_SHORT).show();
                logout();
                break;
            }
            case R.id.menu_drawer_settings: {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*Intent intent = new Intent(MainActivity.this, QRListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        */
                        finish();
                    }
                }, 250);
                break;
            }
            default:{
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        String tipo = Singleton.getInstance().usuarioLogeado.getTipo().toLowerCase();
        if(!tipo.equals("cliente")) {
            if(tipo.equals("administrador")) {
                getMenuInflater().inflate(R.menu.tres_puntos_admin, menu);
            }
            else{
                getMenuInflater().inflate(R.menu.tres_puntos_banda, menu);
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.adminCrear:
                Intent intent = new Intent(MainActivity.this, crear_noticia.class);
                startActivity(intent);
                finish();
                break;
            case R.id.adminEliminar:
                newsFragment.deleteNoticia = true;
                Toast.makeText(this, "Seleccione la noticia que desea eliminar",Toast.LENGTH_SHORT).show();
                break;

            case R.id.CrearBandaAdmin:
                break;

            case R.id.EliminarNoticiaAdmin:
                break;

            case R.id.CrearNoticiaAdmin:
                break;

            case R.id.EliminarUsuarioAdmin:
                break;
        }
        return true;
    }
    private void logout(){
        Singleton.getInstance().usuarioLogeado = null;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
